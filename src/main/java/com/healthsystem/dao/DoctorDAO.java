package com.healthsystem.dao;

import com.healthsystem.entity.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private List<Doctor> doctors = new ArrayList<>();

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    public Doctor getDoctorById(int id) {
        return doctors.stream()
                .filter(doctor -> doctor.getName().hashCode() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateDoctor(int id, Doctor updatedDoctor) {
        int index = -1;
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getName().hashCode() == id) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            doctors.set(index, updatedDoctor);
        }
    }

    public void deleteDoctor(int id) {
        doctors.removeIf(doctor -> doctor.getName().hashCode() == id);
    }
}
