package file_signs.vms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import file_signs.vms.model.entity.Medical_rol;
import file_signs.vms.service.MedicalRol_Service;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rol_medical")
public class MedicalRol_Controller {
    
    @Autowired
    private MedicalRol_Service medicalRolService;

    // Get Medical Rol
    @GetMapping("/")
    public ResponseEntity<List<Medical_rol>> getMedicalRol(){
        List<Medical_rol> medicalRol = medicalRolService.getMedicalRol();
        return ResponseEntity.ok(medicalRol);
    }

    // Get Medical Rol by ID
    @GetMapping("/{rol_id}")
    public ResponseEntity<Medical_rol> getMedicalRolById(@PathVariable Short rol_id){
        Medical_rol medicalRol = medicalRolService.getMedicalRolById(rol_id);
        return ResponseEntity.ok(medicalRol);
    }

    // Insert Medical Rol
    @PostMapping("/createMedicalRol")
    public ResponseEntity<Medical_rol> insertMedicalRol(@RequestBody Medical_rol medicalRol){
        Medical_rol newMedicalRol = medicalRolService.insertMedicalRol(medicalRol);
        return ResponseEntity.ok(newMedicalRol);
    }

    // Insert Medical Rol List
    @PostMapping("/createMedicalRolList")
    public ResponseEntity<List<Medical_rol>> insertMedicalRolList(@RequestBody List<Medical_rol> medicalRol){
        List<Medical_rol> newMedicalRolList = medicalRolService.insertMedicalRolList(medicalRol);
        return ResponseEntity.ok(newMedicalRolList);
    }

    // Update Medical Rol
    @PostMapping("/updateMedicalRol/{rol_id}")
    public ResponseEntity<Medical_rol> updateMedicalRol(@PathVariable Short rol_id, @RequestBody Medical_rol medicalRol){
        Medical_rol updateMedicalRol = medicalRolService.updateMedicalRol(rol_id, medicalRol);
        return ResponseEntity.ok(updateMedicalRol);
    }

    // Delete Medical Rol
    @DeleteMapping("/{rol_id}")
    public ResponseEntity<Void> deleteMedicalRol(@PathVariable Short rol_id){
        medicalRolService.deleteMedicalRol(rol_id);
        return ResponseEntity.ok().build();
    }
    


}
