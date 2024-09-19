import java.util.ArrayList;
import java.util.List;

class DentalClinic {
    private static DentalClinic instance;
    private List<Patient> patients;

    private DentalClinic() {
        patients = new ArrayList<>();
    }

    public static synchronized DentalClinic getInstance() {
        if (instance == null) {
            instance = new DentalClinic();
        }
        return instance;
    }

    public void registerProcedure(DentalProcedure procedure, String patientName, String diagnosis) {
        Patient patient = new Patient(patientName, procedure.getType(), diagnosis);
        patients.add(patient);
        System.out.println("Registering procedure for " + patientName);
    }

    public void removePatient(String patientName) {
        patients.removeIf(p -> p.getName().equals(patientName));
        System.out.println("Patient " + patientName + " has been removed.");
    }

    public List<Patient> getPatientHistory() {
        return patients;
    }
}

class Patient {
    private String name;
    private String procedure;
    private String diagnosis;

    public Patient(String name, String procedure, String diagnosis) {
        this.name = name;
        this.procedure = procedure;
        this.diagnosis = diagnosis;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Patient: " + name + ", Procedure: " + procedure + ", Diagnosis: " + diagnosis;
    }
}

abstract class DentalProcedure {
    public abstract String getType();
}

class Endodontics extends DentalProcedure {
    @Override
    public String getType() {
        return "Endodontics (Root Canal Treatment)";
    }
}

class ToothExtraction extends DentalProcedure {
    @Override
    public String getType() {
        return "Tooth Extraction";
    }
}

class DentalImplant extends DentalProcedure {
    @Override
    public String getType() {
        return "Dental Implant";
    }
}

class Whitening extends DentalProcedure {
    @Override
    public String getType() {
        return "Dental Whitening";
    }
}

class DentalCrown extends DentalProcedure {
    @Override
    public String getType() {
        return "Dental Crown";
    }
}

class CompositeResin extends DentalProcedure {
    @Override
    public String getType() {
        return "Composite Resin (Dental Filling)";
    }
}

class Bruxism extends DentalProcedure {
    @Override
    public String getType() {
        return "Bruxism (Night Guard)";
    }
}

interface ProcedureFactory {
    DentalProcedure createProcedure();
}

class EndodonticsFactory implements ProcedureFactory {
    @Override
    public DentalProcedure createProcedure() {
        return new Endodontics();
    }
}

class ToothExtractionFactory implements ProcedureFactory {
    @Override
    public DentalProcedure createProcedure() {
        return new ToothExtraction();
    }
}

class DentalImplantFactory implements ProcedureFactory {
    @Override
    public DentalProcedure createProcedure() {
        return new DentalImplant();
    }
}

class WhiteningFactory implements ProcedureFactory {
    @Override
    public DentalProcedure createProcedure() {
        return new Whitening();
    }
}

class DentalCrownFactory implements ProcedureFactory {
    @Override
    public DentalProcedure createProcedure() {
        return new DentalCrown();
    }
}

class CompositeResinFactory implements ProcedureFactory {
    @Override
    public DentalProcedure createProcedure() {
        return new CompositeResin();
    }
}

class BruxismFactory implements ProcedureFactory {
    @Override
    public DentalProcedure createProcedure() {
        return new Bruxism();
    }
}

public class Main {
    public static void main(String[] args) {
        DentalClinic clinic = DentalClinic.getInstance();

        ProcedureFactory endodonticsFactory = new EndodonticsFactory();
        DentalProcedure endodontics = endodonticsFactory.createProcedure();
        clinic.registerProcedure(endodontics, "Ana Lopez", "Severe pain in tooth 12.");

        ProcedureFactory extractionFactory = new ToothExtractionFactory();
        DentalProcedure extraction = extractionFactory.createProcedure();
        clinic.registerProcedure(extraction, "Carlos Ruiz", "Fractured and non-restorable tooth.");

        List<Patient> history = clinic.getPatientHistory();
        System.out.println("Patient History:");
        for (Patient p : history) {
            System.out.println(p);
        }

        clinic.removePatient("Ana Lopez");
        
        history = clinic.getPatientHistory();
        System.out.println("Updated Patient History:");
        for (Patient p : history) {
            System.out.println(p);
        }
    }
}

