package com.uis.lovpets.controller;

import com.uis.lovpets.dto.PublicacionDTO;
import com.uis.lovpets.dto.SolicitudDTO;
import com.uis.lovpets.model.Usuario;
import com.uis.lovpets.repository.IUsuarioRepository;
import com.uis.lovpets.service.interfaces.IPublicacionService;
import com.uis.lovpets.service.interfaces.ISolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/solicitud")
public class SolicitudController {

    private ISolicitudService iSolicitudService;
    private IPublicacionService iPublicacionService;
    private IUsuarioRepository iUsuarioRepository;


    @GetMapping("/all")
    public ResponseEntity<List<SolicitudDTO>> getAllSolicitudes(){

        try {

            List<SolicitudDTO> solicitudDTOList = this.iSolicitudService.getAll();
            return new ResponseEntity<>(solicitudDTOList, HttpStatus.OK);

        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/userID/{id}")
    public ResponseEntity<List<SolicitudDTO>> getAllByUserId(@PathVariable(value = "id") Long id){

        try {

            List<SolicitudDTO> solicitudDTOList = this.iSolicitudService.getAll();

            if (solicitudDTOList == null) {
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                }

            else {

                List<SolicitudDTO> solicitudDTOResponse = new ArrayList<>();

                for (SolicitudDTO solicitudDTO:solicitudDTOList) {
                    if (solicitudDTO.getIdUsuario().equals(id)) {
                        solicitudDTOResponse.add(solicitudDTO);
                    }
                }

                if (solicitudDTOResponse.isEmpty()){
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                }
                else {
                    return new ResponseEntity<>(solicitudDTOResponse, HttpStatus.OK);
                }
            }
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/publicacionID/{id}")
    public ResponseEntity<List<SolicitudDTO>> getAllByPublicacionId(@PathVariable(value = "id") Long id){

        try {

            List<SolicitudDTO> solicitudDTOList = this.iSolicitudService.getAll();

            if (solicitudDTOList == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            else {

                List<SolicitudDTO> solicitudDTOResponse = new ArrayList<>();

                for (SolicitudDTO solicitudDTO:solicitudDTOList) {
                    if (solicitudDTO.getIdPublicacion().equals(id) && solicitudDTO.getEstado().equals(2L)) {
                        solicitudDTOResponse.add(solicitudDTO);
                    }
                }

                if (solicitudDTOResponse.isEmpty()){
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                }
                else {
                    return new ResponseEntity<>(solicitudDTOResponse, HttpStatus.OK);
                }
            }
            /*List<Solicitud> solicitudListResponse = new ArrayList<>();
        for (Solicitud solicitud: solicitudList) {
            if(solicitud.getEstado().equals(1L) || solicitud.getEstado().equals(2L)){
                solicitudListResponse.add(solicitud);
            }
        }*/

        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<SolicitudDTO> getSolicitudById(@PathVariable(value = "id") Long id){

        try {

            SolicitudDTO solicitudDTO = this.iSolicitudService.getOne(id);
            if (solicitudDTO==null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(solicitudDTO, HttpStatus.OK);
            }

        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<?> crearSolicitud(@RequestBody SolicitudDTO solicitudDTO){

        try {

            PublicacionDTO publicacionDTO = this.iPublicacionService.getOne(solicitudDTO.getIdPublicacion());
            if (solicitudDTO.getIdUsuario().equals(publicacionDTO.getIdUsuario())){
                return new ResponseEntity<>("Error: El usuario que hace la solicitud no puede ser el mismo usuario que hace la publicación", HttpStatus.CONFLICT);
            }

            HashMap<Boolean, String> hashMap = this.validaciones(solicitudDTO);
            if (hashMap.containsKey(false)){
                return new ResponseEntity<>("Error: " + hashMap.get(false), HttpStatus.BAD_REQUEST);
            }
            Date date = new Date();
            solicitudDTO.setFechaRegistro(date);
            solicitudDTO.setEstado(2L);
            if (!solicitudDTO.getOtrasMascotas()){
                solicitudDTO.setMascotas("");
            }
            Boolean aBoolean = this.iSolicitudService.crearSolicitud(solicitudDTO);
            return new ResponseEntity<>(aBoolean, HttpStatus.OK);

        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @PutMapping
    public ResponseEntity<?> actualizarSolicitud(@RequestBody SolicitudDTO solicitudDTO){

        try {
            if (solicitudDTO.getId()==null){
                return new ResponseEntity<>("Error: El parametro id es obligatorio", HttpStatus.BAD_REQUEST);
            }
            HashMap<Boolean, String> hashMap = this.validaciones(solicitudDTO);
            if (hashMap.containsKey(false)){
                return new ResponseEntity<>("Error: " + hashMap.get(false), HttpStatus.BAD_REQUEST);
            }

            Date date = new Date();
            solicitudDTO.setFechaRegistro(date);
            solicitudDTO.setEstado(2L);
            if (!solicitudDTO.getOtrasMascotas()){
                solicitudDTO.setMascotas("");
            }

            Boolean aBoolean = this.iSolicitudService.actualizarSolicitud(solicitudDTO);
            return new ResponseEntity<>(aBoolean, HttpStatus.OK);

        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> eliminarSolicitud(@PathVariable(value = "id") Long id){

        try {
            if (this.iSolicitudService.getOne(id) == null ){
                return new ResponseEntity<>("No se encontró la solicitud con el id " + id, HttpStatus.OK);
            }

            Boolean aBoolean = this.iSolicitudService.eliminarSolicitud(id);
            return new ResponseEntity<>(aBoolean, HttpStatus.OK);

        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping("/aceptar")
    public ResponseEntity<?> aceptarSolicitud(@RequestParam Long idSolicitud) {

        try {

            Boolean aBoolean = this.iSolicitudService.aceptarSolicitud(idSolicitud);

            if (aBoolean){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
            }

        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping("/rechazar")
    public ResponseEntity<?> rechazarSolicitud(@RequestParam Long idSolicitud) {

        try {

            Boolean aBoolean = this.iSolicitudService.rechazarSolicitud(idSolicitud);

            if (aBoolean){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
            }

        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }





    private HashMap<Boolean, String> validaciones(SolicitudDTO solicitudDTO){

        HashMap<Boolean, String> hashMap = new HashMap<>();

        if (solicitudDTO.getOtrasMascotas()==null){
            hashMap.put(false, "El parametro otrasMascotas es obligatorio");
            return hashMap;
        }

        if (solicitudDTO.getOtrasMascotas() && (solicitudDTO.getMascotas() == null || solicitudDTO.getMascotas().isEmpty())){
            hashMap.put(false, "El parametro mascotas es obligatorio ya que se indicó que hay otras mascotas");
            return hashMap;
        }

        if (solicitudDTO.getCondiciones()==null || solicitudDTO.getCondiciones().isEmpty() ){
            hashMap.put(false, "El parametro condiciones es obligatorio");
            return hashMap;
        }

        if (solicitudDTO.getIdPublicacion()==null){
            hashMap.put(false, "El parametro idPublicacion es obligatorio");
            return hashMap;
        }

        if (this.iPublicacionService.getOne(solicitudDTO.getIdPublicacion()) == null){
            hashMap.put(false, "El idPublicacion ingresado no corresponde a ninguna publicacion registrada");
            return hashMap;
        }

        if (solicitudDTO.getIdUsuario()==null){
            hashMap.put(false, "El parametro idUsuario es obligatorio");
            return hashMap;
        }

        Usuario usuario = this.iUsuarioRepository.findById(solicitudDTO.getIdUsuario()).orElse(null);
        if (usuario==null){
            hashMap.put(false, "El idUsuario ingresado no corresponde a ningun usuario registrado");
            return hashMap;
        }

        hashMap.put(true, "");
        return hashMap;
    }



    @Autowired
    public void setiSolicitudService(ISolicitudService iSolicitudService) {
        this.iSolicitudService = iSolicitudService;
    }

    @Autowired
    public void setiPublicacionService(IPublicacionService iPublicacionService) {
        this.iPublicacionService = iPublicacionService;
    }

    @Autowired
    public void setiUsuarioRepository(IUsuarioRepository iUsuarioRepository) {
        this.iUsuarioRepository = iUsuarioRepository;
    }
}
