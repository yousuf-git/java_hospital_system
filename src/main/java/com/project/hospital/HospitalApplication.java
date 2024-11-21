package com.project.hospital;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
// import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.github.javafaker.Faker;
import com.project.hospital.entities.Admin;
import com.project.hospital.entities.Appointment;
import com.project.hospital.entities.Department;
import com.project.hospital.entities.Doctor;
import com.project.hospital.entities.Patient;
import com.project.hospital.entities.Payment;
import com.project.hospital.entities.Schedule;
import com.project.hospital.repos.AdminRepo;
import com.project.hospital.repos.AppointmentRepo;
import com.project.hospital.repos.DepartmentRepo;
import com.project.hospital.repos.DoctorRepo;
import com.project.hospital.repos.PatientRepo;
import com.project.hospital.repos.PaymentRepo;
import com.project.hospital.repos.ScheduleRepo;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}}
		/* ---------- > Run only once for generating dummy data
		ApplicationContext appContext = SpringApplication.run(HospitalApplication.class, args);

		// Filling the database with some dummy data using library Faker
		Faker faker = new Faker();

		Admin dummyAdmin = Admin.builder()
				.adminName("Harry")
				.adminEmail("harry@gmail.com")
				.adminPassword("harry")
				.adminPhone(1234567890L)
				.build();

		// Doctors
		// Doctor doctor1 = Doctor.builder().docName(faker.name().fullName()).docPhone(faker.phoneNumber().cellPhone())
		// 		.docGender("Male").build();

		// Using a loop to create 25 doctors including male and female belonging to different departments and having different schedules

		List<Doctor> docList = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			Doctor doctor = Doctor.builder()
							.docName(faker.name().fullName())
							.docEmail(faker.internet().emailAddress())
							.docPassword(faker.internet().password())
							.docPhone(Long.parseLong(faker.phoneNumber().phoneNumber().replaceAll("\\D", "")))
							.docGender(faker.options().option("M", "F"))
							.docFees((double) faker.number().randomDouble(2, 100, 1000))
							.build();
			docList.add(doctor);
		}

		// 5 Departments
		List<String> departments = Arrays.asList(
								"Cardiology",
								"Dermatology",
								"Emergency Department",
								"Gastroenterology",
								"General Surgery"
								// "Neurology",
								// "Obstetrics and Gynecology",
								// "Oncology",
								// "Orthopedics",
								// "Pediatrics",
								// "Psychiatry"
								);

		List<Department> depList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Department department = Department.builder()
							// .depName(departments.get(faker.number().numberBetween(0, departments.size() - 1)))
							.depName(departments.get(i))
							.depPhone(Long.parseLong(
								faker.phoneNumber().phoneNumber().replaceAll("\\D", "")))
							.build();
			depList.add(department);
		}

		// Each one docotor from each deaprt will have same schedule (randomly generated) so we need to create 5 schedules

		// 5 Schedules
		List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
		List<Schedule> schList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			// Faker gives us time as a string so I Converted String to LocalTime
			// LocalTime schFrom = LocalTime.parse(schedule.getSchFrom());
			
			LocalTime schFrom = getRandomRoundTime();
			Schedule schedule = Schedule.builder()
			// .schDay(faker.options().option("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"))
					.schDay(days.get(i))
					.schFrom(schFrom)
					.schTo(schFrom.plusHours(2))
			.build();
			
			schList.add(schedule);
		}

		// 15 Patients
		// List<Patient> patList = new ArrayList<>();
		// for (int i = 0; i < 15; i++) {
		// 	// phoneNumberStr.replaceAll("\\D", ""); // remove non-numeric characters
		// 	Patient patient = Patient.builder()
		// 					.patName(faker.name().fullName())
		// 					.patPhone(Long.parseLong(faker.phoneNumber().phoneNumber().replaceAll("\\D", "")))
		// 					.patGender(faker.options().option("M", "F"))
		// 					.patDob(LocalDate.parse(faker.date().birthday().toString()))
		// 					.build();
		// 	patList.add(patient);
		// }
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 15 Patients
        List<Patient> patList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            // phoneNumberStr.replaceAll("\\D", ""); // remove non-numeric characters
            Patient patient = Patient.builder()
                            .patName(faker.name().fullName())
							.patEmail(faker.internet().emailAddress())
							.patPassword(faker.internet().password())
                            .patPhone(Long.parseLong(faker.phoneNumber().phoneNumber().replaceAll("\\D", "")))
                            .patGender(faker.options().option("M", "F"))
                            .patDob(LocalDate.parse(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString(), formatter))
                            .build();
            patList.add(patient);
        }

		// 15 Appointments
		List<Appointment> appList = new ArrayList<>();
		// List of available rooms like H-101, H-102, H-103, H-104, H-105, H-106, H-107, H-108, H-109, H-110
		List<String> rooms = Arrays.asList("H-101", "H-102", "H-103", "H-104", "H-105", "H-106", "H-107", "H-108", "H-109", "H-110", "H-111", "H-112", "H-113", "H-114", "H-115", "H-116", "H-117", "H-118", "H-119", "H-120", "H-121", "H-122", "H-123", "H-124", "H-125", "H-126", "H-127", "H-128", "H-129", "H-130", "H-131", "H-132", "H-133", "H-134", "H-135", "H-136", "H-137", "H-138", "H-139", "H-140", "H-141", "H-142", "H-143", "H-144", "H-145", "H-146", "H-147", "H-148", "H-149", "H-150");

		for (int i = 0; i < 15; i++) {
			Appointment appointment = Appointment.builder()
					.appDate(faker.date().future(1, java.util.concurrent.TimeUnit.HOURS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
					
					// .appTime(parseTime(faker.date().future(1, java.util.concurrent.TimeUnit.HOURS).toInstant().toString()))
					.appTime(getRandomRoundTime())
					
					.appRoom(rooms.get(faker.number().numberBetween(0, rooms.size() - 1)))
					.appStatus(faker.options().option("Completed", "Pending", "Cancelled"))
					.appReason(faker.lorem().sentence())
					.build();
			appList.add(appointment);
		}

		// 15 Payments
		List<Payment> payList = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			Payment payment = Payment.builder()
							.payAmount((float) faker.number().randomDouble(2, 100, 1000))
							// localDateTime that can be parsed by faker
							.payDateTime(LocalDateTime.parse(faker.date().future(1, java.util.concurrent.TimeUnit.HOURS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString()))
							// Payment method can be any of these: Cash, Credit or Debit Card, eWallet
							.payMethod(faker.options().option("Cash", "Credit or Debit Card", "eWallet"))
							.build();
			payList.add(payment);
		}

		// Total data that we have here:
		// 25 Doctors
		// 5 Departments
		// 5 Schedules
		// 15 Patients
		// 15 Appointments
		// 15 Payments

		// Now Attaching relevant entities to each other
		// Each doctor belongs to a department so I'm distributing 5 doctors to each department
		for (int i = 0; i < depList.size(); i++) {
			int j = 0;
			while (j < 5) {
				docList.get(i * 5 + j).setDepartment(depList.get(i));
				// docList.get(i * 5 + j).setSchedule(schList.get(i));
				j++;
			}
			// This will assign department 1 to doctors 1-5, department 2 to doctors 6-10, and so on
		}

		// Each doctor has schedule so I'm assigning each doctor from each department 1 same schedule means 5 doctors will have same schedule and then next 5 doctors will have next schedule and so on
		for (int i = 0; i < docList.size(); i++) {
			docList.get(i).setSchedule(schList.get(i % 5)); // 0%5=0, 1%5=1, 2%5=2, 3%5=3, 4%5=4, 5%5=0, 6%5=1, 7%5=2, 8%5=3, 9%5=4, 10%5=0, and so on
			// This will assign schedule 1 to doc 1, 6, 11, 16, 21, schedule 2 to doc 2, 7, 12, 17, 22, and so on
		}

		// Each appointment belongs to a patient and a doctor
		// Supposing that 5 group of 3 patients were delt by 5 doctors from 5 diff departments
		// Patients 1-3 will have appointment with doctor 1, patients 4-6 with doctor 2, and so on
		for (int i = 0; i < appList.size(); i++) {
			appList.get(i).setPatient(patList.get(i));
			appList.get(i).setDoctor(docList.get(i / 3));
		}

		// Each payment belongs to a patient based on the appointments
		for (int i = 0; i < payList.size(); i++) {
			payList.get(i).setPatient(appList.get(i).getPatient());
		}


		// --------------- Printing the generated data ---------------
		
		// System.out.println("\n -------------- Doctors -------------- \n");
		// docList.forEach(System.out::println);

		// System.out.println("\n -------------- Departments -------------- \n");
		// depList.forEach(System.out::println);

		// System.out.println("\n -------------- Schedules -------------- \n");
		// schList.forEach(System.out::println);

		// System.out.println("\n -------------- Patients -------------- \n");
		// patList.forEach(System.out::println);

		// System.out.println("\n -------------- Payments -------------- \n");
		// payList.forEach(System.out::println);

		// Storing the data in the database using respective repos
		// MyRepo repo = appContext.getBean(MyRepo.class);

		DoctorRepo doctorRepo = appContext.getBean(DoctorRepo.class);
		DepartmentRepo departmentRepo = appContext.getBean(DepartmentRepo.class);
		ScheduleRepo scheduleRepo = appContext.getBean(ScheduleRepo.class);
		PatientRepo patientRepo = appContext.getBean(PatientRepo.class);
		AppointmentRepo appointmentRepo = appContext.getBean(AppointmentRepo.class);
		PaymentRepo paymentRepo = appContext.getBean(PaymentRepo.class);
		AdminRepo adminRepo = appContext.getBean(AdminRepo.class);
		adminRepo.save(dummyAdmin);

		// Saving entities in sequence so that the dependencies are resolved and avoiding any constraint violation and saving entities more than once

		saveAllEntities(departmentRepo, scheduleRepo, doctorRepo, patientRepo, appointmentRepo, paymentRepo, depList, schList, docList, patList, appList, payList);
		
		
	}
	
	
	// private static LocalTime parseTime(String dateTimeString) {
	// 	try {
	// 		// Extract the time part from the date-time string
	// 		String timeString = dateTimeString.substring(11, 19);
	// 		return LocalTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_TIME);
	// 	} catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
	// 		e.printStackTrace();
	// 		return null;
	// 	}
	// }

	private static LocalTime getRandomRoundTime() {
        int randomHour = ThreadLocalRandom.current().nextInt(0, 24);
        int randomMinute = ThreadLocalRandom.current().nextInt(0, 4) * 15; // 0, 15, 30, 45
		// DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.of(randomHour, randomMinute).truncatedTo(ChronoUnit.MINUTES);
    }

	@Transactional
	private static void saveAllEntities(DepartmentRepo departmentRepo, ScheduleRepo scheduleRepo, DoctorRepo doctorRepo, PatientRepo patientRepo, AppointmentRepo appointmentRepo, PaymentRepo paymentRepo, List<Department> depList, List<Schedule> schList, List<Doctor> docList, List<Patient> patList, List<Appointment> appList, List<Payment> payList) {
		departmentRepo.saveAll(depList);
		scheduleRepo.saveAll(schList);
		doctorRepo.saveAll(docList);
		patientRepo.saveAll(patList);
		appointmentRepo.saveAll(appList);
		paymentRepo.saveAll(payList);
}


}

*/
