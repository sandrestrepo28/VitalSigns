package file_signs.vms.service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import file_signs.vms.model.entity.Vital;
import file_signs.vms.repository.Vital_Repository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
@Valid
public class Vital_Service {

    @Autowired
    private Vital_Repository vitalRepository;

    private final ChatClient chatClient;

    public Vital_Service(ChatClient.Builder builder) {
        this.chatClient = builder
                .build();
    }

    // Get vital by Patient
    public List<Vital> getVitalByPatient(Long patient_id) {
        return vitalRepository.getVitalByPatient(patient_id);
    }

    // Get vital by Id_vital
    public Vital getVitalById(UUID vital_id) {
        return vitalRepository.findById(vital_id)
                .orElseThrow(() -> new RuntimeException("Vital not found"));
    }

    // Save vital
    @Transactional
    public Vital saveVital(@Valid Vital vital) {

        if (Objects.isNull(vital.getAssignment_id())) {
            throw new RuntimeException("Assignment ID cannot be null");
        }

        return vitalRepository.save(vital);
    }

    // Delete vital
    public void deleteVital(UUID vital_id) {
        vitalRepository.deleteById(vital_id);
    }

    // Chat Vital
    public String chatVital(UUID vital_id) {

        Vital vital = vitalRepository.findById(vital_id)
                .orElseThrow(() -> new RuntimeException("Vital not found"));

        String prompt = String.format(
                """
                        Actua como un profesional medico y evalua el siguiente caso. 
                        Por favor, responde en español de manera clara y precisa:
                        - Frecuencia cardiaca: %d BPM
                        - Saturacion: %d Spo2
                        - Frecuencia respiratoria: %d RPM
                        - Temperatura: %d C°
                        - Glucosa en sangre: %d mg/dL
                        - Presion arterial: %s mmHg
                        - Nota medica: %s
                        """,
                vital.getHeart_rate(),
                vital.getSaturation(),
                vital.getRespiratory_rate(),
                vital.getTemperature(),
                vital.getBlood_glucose(),
                vital.getBlood_pressure(),
                vital.getNote_medical());

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

}
