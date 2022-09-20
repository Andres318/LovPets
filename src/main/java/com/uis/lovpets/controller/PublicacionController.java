package com.uis.lovpets.controller;

import com.uis.lovpets.dto.PublicacionDTO;
import com.uis.lovpets.model.Usuario;
import com.uis.lovpets.repository.IUsuarioRepository;
import com.uis.lovpets.service.interfaces.IPublicacionService;
import com.uis.lovpets.service.interfaces.ITipoMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/publicacion")
public class PublicacionController {

    private IPublicacionService iPublicacionService;
    private ITipoMascotaService iTipoMascotaService;
    private IUsuarioRepository iUsuarioRepository;


    @GetMapping("/all")
    public ResponseEntity<List<PublicacionDTO>> getAllPublicaciones(){
        try {
            List<PublicacionDTO> publicacionDTOS = this.iPublicacionService.getAll();
            return  new ResponseEntity<>(publicacionDTOS, HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/userID/{id}")
    public ResponseEntity<List<PublicacionDTO>> getPublicacionesByUserId(@PathVariable(value = "id") Long id){
        try {
            List<PublicacionDTO> publicacionDTOList = this.iPublicacionService.getAll();

            if (publicacionDTOList == null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            else {
                List<PublicacionDTO> publicacionDTOListResponse = new ArrayList<>();
                for (PublicacionDTO publicacionDTO: publicacionDTOList) {
                    if (publicacionDTO.getIdUsuario().equals(id)){
                        publicacionDTOListResponse.add(publicacionDTO);
                    }
                }
                if (publicacionDTOListResponse.isEmpty()){
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                }
                else {
                    return new ResponseEntity<>(publicacionDTOListResponse, HttpStatus.OK);
                }
            }
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<PublicacionDTO> getPublicacionById(@PathVariable(value = "id") Long id){
        try {

            PublicacionDTO publicacionDTO = this.iPublicacionService.getOne(id);
            if (publicacionDTO == null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(publicacionDTO, HttpStatus.OK);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<PublicacionDTO>> getAllPublicacionesByTipoOrCiudad(@RequestParam(required = false) Long tipo, @RequestParam(required = false) Long ciudad){

        try {
            if (tipo == null && ciudad == null){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            List<PublicacionDTO> publicacionesResponse = new ArrayList<>();
            
            List<PublicacionDTO> publicacionesList = this.iPublicacionService.getAll();

            if (tipo != null && ciudad != null){
                for (PublicacionDTO publicacionDTO : publicacionesList) {
                    if (publicacionDTO.getIdTipoMascota().equals(tipo) && publicacionDTO.getIdCiudad().equals(ciudad)){
                        publicacionesResponse.add(publicacionDTO);
                    }
                }
            }
            else {
                for (PublicacionDTO publicacionDTO : publicacionesList) {
                     if (tipo != null && publicacionDTO.getIdTipoMascota().equals(tipo)) {
                        publicacionesResponse.add(publicacionDTO);
                    } else if (ciudad != null && publicacionDTO.getIdCiudad().equals(ciudad)) {
                        publicacionesResponse.add(publicacionDTO);
                    }
                }

            }
            return new ResponseEntity<>(publicacionesResponse, HttpStatus.OK);

        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<?> crearPublicacion(@RequestBody PublicacionDTO publicacionDTO){

        try {
            HashMap<Boolean, String> hashMap = this.validaciones(publicacionDTO);
            if (hashMap.containsKey(false)){
                return new ResponseEntity<>("Error: " + hashMap.get(false), HttpStatus.BAD_REQUEST);
            }

            Boolean aBoolean = this.iPublicacionService.crearPublicacion(publicacionDTO);
            return new ResponseEntity<>(aBoolean, HttpStatus.OK);
        }

        catch (Exception exception) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping
    public ResponseEntity<?> actualizarPublicacion(@RequestBody PublicacionDTO publicacionDTO){

        try {
            if (publicacionDTO.getId() == null){
                return new ResponseEntity<>("Error: El parametro id es obligatorio", HttpStatus.BAD_REQUEST);
            }
            HashMap<Boolean, String> hashMap = this.validaciones(publicacionDTO);
            if (hashMap.containsKey(false)){
                return new ResponseEntity<>("Error: " + hashMap.get(false), HttpStatus.BAD_REQUEST);
            }

            Boolean aBoolean = this.iPublicacionService.actualizarPublicacion(publicacionDTO);
            return new ResponseEntity<>(aBoolean, HttpStatus.OK);

        }
        catch (Exception exception) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> eliminarPublicacion(@PathVariable(value = "id") Long id){

        try {
            if (this.iPublicacionService.getOne(id) == null){
                return new ResponseEntity<>("No se encontró la publicacion con el id " + id, HttpStatus.OK);
            }
            Boolean aBoolean = this.iPublicacionService.eliminarPublicacion(id);
            return new ResponseEntity<>(aBoolean, HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    private HashMap<Boolean, String> validaciones(PublicacionDTO publicacionDTO){
        HashMap<Boolean, String> hashMap = new HashMap<>();

        if (publicacionDTO.getNombre() == null || publicacionDTO.getNombre().isEmpty() ){
            hashMap.put(false, "El parametro nombre es obligatorio");
            return hashMap;
        }

        if (publicacionDTO.getImagenB64() == null || publicacionDTO.getImagenB64().isEmpty() ){
            hashMap.put(false, "El parametro imagenB64 es obligatorio");
            return hashMap;
        }

        /*if (publicacionDTO.getEdad() == null ){
            hashMap.put(false, "El parametro edad es obligatorio");
            return hashMap;
        }

        if (publicacionDTO.getEdad() <= 0 ){
            hashMap.put(false, "El parametro edad es debe ser positivo");
            return hashMap;
        }

        if (publicacionDTO.getUnidadEdad() == null || publicacionDTO.getUnidadEdad().isEmpty() ){
            hashMap.put(false, "El parametro unidadEdad es obligatorio");
            return hashMap;
        }
*/
        if (publicacionDTO.getGenero() == null || publicacionDTO.getGenero().isEmpty() ){
            hashMap.put(false, "El parametro genero es obligatorio");
            return hashMap;
        }

        /*if (publicacionDTO.getRaza() == null || publicacionDTO.getRaza().isEmpty() ){
            hashMap.put(false, "El parametro raza es obligatorio");
            return hashMap;
        }*/

        if (publicacionDTO.getIdTipoMascota() == null ){
            hashMap.put(false, "El parametro idTipoMascota es obligatorio");
            return hashMap;
        }

        if (this.iTipoMascotaService.getOne(publicacionDTO.getIdTipoMascota())==null){
            hashMap.put(false, "El idTipoMascota ingresado no corresponde a ningun tipo de mascota registrado");
            return hashMap;
        }

        if (publicacionDTO.getIdUsuario() == null){
            hashMap.put(false, "El parametro idUsuario es obligatorio");
            return hashMap;
        }

        Usuario usuario = this.iUsuarioRepository.findById(publicacionDTO.getIdUsuario()).orElse(null);
        if (usuario==null){
            hashMap.put(false, "El idUsuario ingresado no corresponde a ningun usuario registrado");
            return hashMap;
        }

        hashMap.put(true, "");
        return hashMap;
    }

    @Autowired
    public void setiPublicacionService(IPublicacionService iPublicacionService) {
        this.iPublicacionService = iPublicacionService;
    }

    @Autowired
    public void setiTipoMascotaService(ITipoMascotaService iTipoMascotaService) {
        this.iTipoMascotaService = iTipoMascotaService;
    }

    @Autowired
    public void setiUsuarioRepository(IUsuarioRepository iUsuarioRepository) {
        this.iUsuarioRepository = iUsuarioRepository;
    }

}
