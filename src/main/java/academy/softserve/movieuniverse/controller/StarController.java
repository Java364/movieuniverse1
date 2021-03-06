package academy.softserve.movieuniverse.controller;

import academy.softserve.movieuniverse.dto.AvatarDTO;
import academy.softserve.movieuniverse.dto.LinksDTO;
import academy.softserve.movieuniverse.dto.StarActivityInMoviesDTO;
import academy.softserve.movieuniverse.dto.StarProfessionDTO;
import academy.softserve.movieuniverse.dto.country.CountryDTO;
import academy.softserve.movieuniverse.dto.gallery.GalleryDTO;
import academy.softserve.movieuniverse.dto.star.StarCreateInfo;
import academy.softserve.movieuniverse.dto.star.StarDTO;
import academy.softserve.movieuniverse.dto.star.StarSearchInfo;
import academy.softserve.movieuniverse.dto.star.StarSearchRequest;
import academy.softserve.movieuniverse.entity.*;
import academy.softserve.movieuniverse.mapper.*;
import academy.softserve.movieuniverse.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/stars")
public class StarController {

    private final StarService starService;
    private final StarProfessionService starProfessionService;
    private final StarMapper mapper;
    private final StarProfessionMapper starProfessionMapper;
    private final LinksService linksService;
    private final LinksMapper linksMapper;
    private final GalleryMapper galleryMapper;
    private final CountryMapper countryMapper;
    private final AvatarMapper avatarMapper;
    private final StarActivityInMoviesMapper starActivityMapper;

    @Autowired
    public StarController(StarService starService, StarProfessionService starProfessionService, StarMapper mapper,
            StarProfessionMapper starProfessionMapper, LinksMapper linksMapper, LinksService linksService,
            GalleryService galleryService, GalleryMapper galleryMapper, CountryMapper countryMapper,
            StarActivityInMoviesMapper starActivityMapper, AvatarMapper avatarMapper,
            ProfessionService professionService) {
        this.starService = starService;
        this.starProfessionService = starProfessionService;
        this.mapper = mapper;
        this.starProfessionMapper = starProfessionMapper;
        this.linksService = linksService;
        this.linksMapper = linksMapper;
        this.galleryMapper = galleryMapper;
        this.countryMapper = countryMapper;
        this.starActivityMapper = starActivityMapper;
        this.avatarMapper = avatarMapper;
    }

	@GetMapping
    public ResponseEntity<List<StarSearchInfo>> showAll(StarSearchRequest starSearchRequest) {
        List<StarSearchInfo> dto = mapper.mapListEntityToStarSearchInfoList(starService.showAll(starSearchRequest));
        dto.forEach(System.out::println);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StarDTO> showOne(@PathVariable Long id) {
        Star star = starService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.mapProfileToDto(star));
    }

    @PostMapping
    public ResponseEntity<StarDTO> create(@RequestBody StarCreateInfo starCreateInfo) {
//	if (starService.validatorCreationAndEditStar(starDTO)) {
//        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
//	}

        Star star = mapper.mapCreateToEntity(starCreateInfo);
        Star starNew = starService.create(star);
        StarDTO starDTO = mapper.mapProfileToDto(starNew);
        return new ResponseEntity<StarDTO>(starDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StarDTO> update(@RequestBody StarDTO starDTO, @PathVariable Long id) {
    	if (starService.validatorCreationAndEditStar(starDTO)) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    	}
        Star star = mapper.mapUpdateToEntity(starDTO, id);
        starService.update(star, id);
        starDTO = mapper.mapCreateToDto(star);
        return new ResponseEntity<StarDTO>(starDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> completelyDelete(@PathVariable Long id) {
        starService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/remove/{id}")
    public ResponseEntity<StarDTO> remove(@PathVariable Long id) {
        starService.remove(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/make-active/{id}")
    public ResponseEntity<StarDTO> makeActive(@PathVariable Long id) {
        starService.makeActive(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}/gallery")
    public ResponseEntity<GalleryDTO> showStarGallery(@PathVariable Long id) {
        Star star = starService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(galleryMapper.mapToDTO(star.getGallery()));
    }

    @PostMapping("/{id}/gallery")
    public ResponseEntity<GalleryDTO> createStarGallery(@PathVariable Long id) {
        Gallery gallery = starService.addNewGallery(id);
        System.out.println(gallery);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(galleryMapper.mapToDTO(starService.findById(id).getGallery()));
    }

    @GetMapping("/{id}/avatar")
    public ResponseEntity<AvatarDTO> showStarAvatar(@PathVariable Long id) {
        Star star = starService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(avatarMapper.mapToDTO(star.getAvatar()));
    }

    @GetMapping("/{id}/links")
    public ResponseEntity<List<LinksDTO>> showLinksByStarId(@PathVariable Long id) {
        Star star = starService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(linksMapper.mapListToDto(star.getLinks()));
    }

    @PostMapping("/{id}/links")
    public ResponseEntity<LinksDTO> addNewLinkForStar(@PathVariable Long id, @RequestBody LinksDTO linksDTO) {
        Star star = starService.findById(id);
        Links link = linksMapper.mapToEntityAndSaveLinks(linksDTO);
        link.setStar(star);
        linksService.saveLinks(link);
        return ResponseEntity.status(HttpStatus.CREATED).body(linksMapper.mapEntityToDto(link));
    }

    @GetMapping("/{id}/countries")
    public ResponseEntity<List<CountryDTO>> showCountriesByStarId(@PathVariable Long id) {
        Star star = starService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(countryMapper.mapListToDto(star.getCountries()));
    }

    @PostMapping("/{id}/countries")
    public ResponseEntity<List<CountryDTO>> addStarCountries(@PathVariable Long id,
            @RequestBody List<CountryDTO> countryDTOS) {
        Star star = starService.findById(id);
        Set<Country> countries = countryMapper.mapCountriesListToEntity(countryDTOS);
        star.setCountries(countries);
        starService.update(star, id);
        return ResponseEntity.status(HttpStatus.OK).body(countryMapper.mapListToDto(star.getCountries()));
    }

    @GetMapping("/{id}/professions")
    public ResponseEntity<List<StarProfessionDTO>> showProfessionsByStarId(@PathVariable Long id) {
        Star star = starService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(starProfessionMapper.mapListEntityToDTO(star.getProfessions()));
    }
    
    @PostMapping("/{id}/professions")
    public ResponseEntity<StarProfessionDTO> createProfession(@RequestBody StarProfessionDTO starProfessionDTO,
            @PathVariable("id") Long starId) {
        StarProfession starProfession = starProfessionMapper.mapToEntity(starProfessionDTO);
        starProfessionService.createStarProfession(starProfession, starId);
        starProfessionDTO = starProfessionMapper.mapToDto(starProfession);
        return new ResponseEntity<StarProfessionDTO>(starProfessionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/roles")
    public ResponseEntity<List<StarActivityInMoviesDTO>> showRolesByStarId(@PathVariable Long id) {
        Star star = starService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(starActivityMapper.mapActivityListsToDto(star.getRoles()));
    }
}
