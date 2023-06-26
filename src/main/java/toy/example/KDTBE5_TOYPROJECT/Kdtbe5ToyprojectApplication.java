package toy.example.KDTBE5_TOYPROJECT;

import db.DBConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import toy.example.KDTBE5_TOYPROJECT.dao.StadiumDao;
import toy.example.KDTBE5_TOYPROJECT.service.StadiumService;

import java.util.Scanner;

@SpringBootApplication
public class Kdtbe5ToyprojectApplication {
	private static StadiumService stadiumService;

	public static void main(String[] args) {
		SpringApplication.run(Kdtbe5ToyprojectApplication.class, args);
		DBConnection.getInstance();

		StadiumDao stadiumDao = StadiumDao.getInstance();
		stadiumService = new StadiumService(stadiumDao);

		Scanner scanner = new Scanner(System.in);
		System.out.print("어떤 기능을 요청하시겠습니까?");
		String userInput = scanner.nextLine();

		if (userInput.startsWith("야구장등록")) {
			String name = userInput.split("=")[1];

			int result = stadiumService.insertStadium(name);

			if (result == 1) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
		} else {
			System.out.println("올바른 기능을 입력해주세요.");
		}
	}
}
