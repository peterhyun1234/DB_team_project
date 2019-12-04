import java.util.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.DataOutputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 자동정렬: ctrl + shift + f

//관리자
//1. 회원 목록 확인 (done)
//2. 회원별 관심사 확인 (done)
//3. 정책 세부사항 수정 (약간 야매로 ㄱㄱ)
//4. 사용자 요청 확인 (done)

//사용자
//1. 정책 키워드 검색 (temp/keyword_search)
//2. 나이 기반 정책 검색 (temp/age_search)
//3. 거주지 기반 정책 검색 (temp/location_search)
//4. 지원분야 기반 검색 (temp/category_search)
//5. 사용자 맞춤 정책 추천 (temp/referral + temp/user_based_referral)
//6. 건의사항 작성 (temp/send_req)

public class policy_app {

	static String Input_str;
	static String ID;
	static String pswd;
	static Scanner scan = new Scanner(System.in);// 문자 입력을 인자로 Scanner 생성

	public static void main(String[] args) {

		System.out.println("================================");
		System.out.println("<로그인>");
		System.out.println("ID:");
		ID = scan.nextLine();
		System.out.println("PASSWORD:");
		pswd = scan.nextLine();

		if (ID.equals("admin") & pswd.equals("admin")) // 관리자 로그인
		{
			admin_program();
		} else if (ID.equals("peterhyun1234@gmail.com") & pswd.equals("1234")) // 사용자 로그인
		{
			user_program();
		} else // 로그인 실패
		{
			signin_failure();
		}
	}

	// 관리자용 프로그램
	public static void admin_program() {

		System.out.println("================================");
		System.out.println("<관리자 기능 목차> \n프로그램을 종료하시려면 exit를 입력해주세요.\n");
		System.out.println("1. 회원 목록 확인");
		System.out.println("2. 회원별 관심사 확인");
		System.out.println("3. 정책 세부사항 수정");
		System.out.println("4. 사용자 요청 확인");
		System.out.println("================================\n");
		System.out.println("원하는 기능의 번호를 입력해주세요:");

		Input_str = scan.nextLine();

		while (!Input_str.equals("exit")) {
			switch (Input_str) {
			case "1":
				show_user_list();
				break;
			case "2":
				show_user_interest();
				break;
			case "3":
				modify_policy();
				break;
			case "4":
				show_user_request();
				break;
			default:
				System.out.println("원하는 기능의 번호를 다시 입력해주세요:");
				break;
			}
			Input_str = scan.nextLine();
		}
		System.out.println("program을 종료합니다.");
		return;
	}

	private static void show_user_list() {

		System.out.println("<1. 회원 목록 확인>");
		GET("http://49.236.136.213:3000/temp/all_users", "user");
	}

	private static void show_user_interest() {

		System.out.println("<2. 회원별 관심사 확인>");
		GET("http://49.236.136.213:3000/temp/users_priority", "user");
	}

	private static void modify_policy() {

		System.out.println("<3. 정책 세부사항 수정>");
		System.out.println("변경을 원하는 정책 번호:");
		String get_p_code = scan.nextLine();
		get_p_code = "5519";
		System.out.println("----- < 정책 번호: " + get_p_code + " > -----");
		GET("http://49.236.136.213:3000/policy/" + get_p_code, "policy");

		String p_code = "'" + get_p_code + "'";
		String title = "'경기여성 취업지원금'";
		String uri = "'https://www.jobaba.net/sprtPlcy/info/view.do?seq=5519'";
		String apply_start = "'2019/12/02 09:00:00'";
		String apply_end = "'2019/12/13 00:00:00'";
		String start_age = "35";
		String end_age = "59";
		String contents = "'참가요건 확인 및 가입 신청'";
		String application_target = "'- 여성- 만 35세~59세- 미취업자- 기준중위소득 100%이하- 경기도 1년 이상 거주자'";
		String dor = "'경기'";
		String si = "'전체'";
		String expiration_flag = "0";

		System.out.println("==============정책수정==============");
		System.out.println("수정 설정을 마친 후 'done'를 입력해주세요.\n");
		System.out.println("1. 정책 제목 수정");
		System.out.println("2. 정책 시작날짜 수정");
		System.out.println("3. 정책 종료날짜 수정");
		System.out.println("4. 지원 최소나이 수정");
		System.out.println("5. 지원 최대나이 수정");
		System.out.println("6. 정책 내용 수정");
		System.out.println("7. 지원 대상 내용 수정");
		System.out.println("================================\n");

		String input_str = scan.nextLine();

		while (!input_str.equals("done")) {
			switch (input_str) {
			case "1":
				System.out.println("수정한 정책 제목을 입력해주세요");
				title = scan.nextLine();
				break;
			case "2":
				System.out.println("수정한 정책 시작날짜를 입력해주세요");
				apply_start = scan.nextLine();
				break;
			case "3":
				System.out.println("수정한 정책 종료날짜를 입력해주세요");
				apply_end = scan.nextLine();
				break;
			case "4":
				System.out.println("수정한 지원 최소나이를 입력해주세요");
				start_age = scan.nextLine();
				break;
			case "5":
				System.out.println("수정한 지원 최대나이를 입력해주세요");
				end_age = scan.nextLine();
				break;
			case "6":
				System.out.println("수정한 정책 내용을 입력해주세요");
				contents = scan.nextLine();
				break;
			case "7":
				System.out.println("수정한 지원 대상 내용을 입력해주세요");
				application_target = scan.nextLine();
				break;
			default:
				System.out.println("수정을 원하는 카테고리를 다시 입력해주세요");
				break;
			}
			input_str = scan.nextLine();
		}

		String urlParameters = "p_code =" + p_code + "&title =" + title + "&uri =" + uri + "&apply_start ="
				+ apply_start + "&apply_end =" + apply_end + "&start_age =" + start_age + "&end_age =" + end_age
				+ "&contents =" + contents + "&application_target =" + application_target + "&dor =" + dor + "&si ="
				+ si + "&expiration_flag =" + expiration_flag;

		System.out.println("urlParameters: " + urlParameters);

		POST("http://49.236.136.213:3000/policy/modify_policy", urlParameters);
	}

	private static void show_user_request() {

		System.out.println("<4. 사용자 요청 확인>");
		GET("http://49.236.136.213:3000/temp/show_all_reqs", "request");
	}

	// 사용자용 프로그램
	public static void user_program() {
		System.out.println("================================");
		System.out.println("<사용자 기능 목차> \n프로그램을 종료하시려면 exit를 입력해주세요.\n");
		System.out.println("1. 정책 키워드 검색");
		System.out.println("2. 나이 기반 정책 검색");
		System.out.println("3. 거주지 기반 정책 검색");
		System.out.println("4. 지원분야 기반 검색");
		System.out.println("5. 사용자 맞춤 정책 추천");
		System.out.println("6. 건의사항 작성");
		System.out.println("================================\n");
		System.out.println("원하는 기능의 번호를 입력해주세요:");
		Input_str = scan.nextLine();

		while (!Input_str.equals("exit")) {
			switch (Input_str) {
			case "1":
				keyword_based_search();
				break;
			case "2":
				age_based_search();
				break;
			case "3":
				location_based_search();
				break;
			case "4":
				category_based_search();
				break;
			case "5":
				suggest_custom_policy();
				break;
			case "6":
				send_request();
				break;
			default:
				System.out.println("원하는 기능의 번호를 다시 입력해주세요:");
				break;
			}
			Input_str = scan.nextLine();
		}
		System.out.println("program을 종료합니다.");
		return;
	}

	private static void keyword_based_search() {
		String keyword;
		
		System.out.println("<1. 정책 키워드 검색>");
		System.out.println("키워드를 입력해주세요");
		keyword = scan.nextLine();

		GET("http://49.236.136.213:3000/temp/keyword_search", "policy");
	}

	private static void age_based_search() {
		// TODO Auto-generated method stub
		System.out.println("<2. 나이 기반 정책 검색>");
		System.out.println("나이를 입력해주세요");
		String age = scan.nextLine();
		
		GET("http://49.236.136.213:3000/temp/age_search", "policy");

	}

	private static void location_based_search() {
		// TODO Auto-generated method stub
		System.out.println("<3. 거주지 기반 정책 검색>");
		System.out.println("거주지를 입력해주세요");
		String location = scan.nextLine();
		
		GET("http://49.236.136.213:3000/temp/location_search", "policy");

	}

	private static void category_based_search() {
		// TODO Auto-generated method stub
		System.out.println("<4. 지원분야 기반 검색>");
		System.out.println("지원분야를 입력해주세요");
		String category = scan.nextLine();
		
		GET("http://49.236.136.213:3000/temp/category_search", "policy");

	}

	private static void suggest_custom_policy() {
		// TODO Auto-generated method stub
		System.out.println("<5. 사용자 맞춤 정책 추천>");
		System.out.println("===========<나를 위한 정책>===========");
		GET("http://49.236.136.213:3000/temp/referral", "policy");
		System.out.println("=======<나와 비슷한 사람이 선호한 정책>=======");
		GET("http://49.236.136.213:3000/temp/user_based_referral", "policy");

	}

	private static void send_request() {
		// TODO Auto-generated method stub
		System.out.println("<6. 건의사항 작성>");

		String recv_uID = "peterhyun1234@gmail.com";
		String recv_category = "감사";
		String recv_contents = "잘썼습니다 감사합니다";
		
		System.out.println("문의의 카테고리를 입력해주세요");
		recv_category = scan.nextLine();
		System.out.println("문의의 내용을 입력해주세요");
		recv_contents = scan.nextLine();
		
		System.out.println("전송하시겠습니까 (예/아니오)");		
		String decision = scan.nextLine();
		
		if(decision.equals("예")) {
			String urlParameters = "uID =" + recv_uID + "&category =" + recv_category + "&contents =" + recv_contents;

			System.out.println("urlParameters: " + urlParameters);

			POST("http://49.236.136.213:3000/temp/send_req", urlParameters);	
		}
		else
		{
			System.out.println("전송에 실패했습니다.");	
		}
	}

	// 로그인 실패
	public static void signin_failure() {
		while (!ID.equals("exit")) {
			System.out.println("<로그인 실패> 프로그램을 종료하시려면 exit를 입력해주세요.");
			System.out.println("ID:");
			ID = scan.nextLine();
			System.out.println("PASSWORD:");
			pswd = scan.nextLine();
			if (ID.equals("admin") & pswd.equals("admin")) // 관리자 로그인
			{
				admin_program();
				break;
			} else if (ID.equals("peterhyun1234@gmail.com") & pswd.equals("1234")) // 사용자 로그인
			{
				user_program();
				break;
			}
		}
		System.out.println("program을 종료합니다.");
		return;
	}

	// https://developers.naver.com/docs/common/openapiguide/apicall.md <-- 자바 REST
	// API 관련!!
	// https://118k.tistory.com/225 <-- 자바 REST API 관련!!
	// https://highcode.tistory.com/6 <-- 자바 regex 관련
	// https://itpangpang.xyz/280 <-- 자바 regex 관련
	// http://egloos.zum.com/js7309/v/11117112 <-- 자바 regex 관련 (replaceall 정규식 사용)

	// REST API - GET
	public static void GET(String requestURL, String table) {
		String user[] = { "uID", "name", "dor", "si", "age", "Employment_sup_priority", "Startup_sup_priority",
				"Life_welfare_priority", "Residential_financial_priority", "sex" };
		String user_change[] = { "\n사용자 ID", "사용자 이름", "사용자 사는 지역(대)", "사용자 사는 지역(소)", "사용자 나이", "취업 관련 정책 선호도",
				"창업 관련 정책 선호도", "복지*생활 정책 선호도", "주거*금융 정책 선호도", "성" };

		String request[] = { "req_code", "req_uID", "req_category", "req_time", "req_contents", "req_flag" };
		String request_change[] = { "\n요청 번호", "요청자 ID", "요청 분류", "요청 시간", "요청 내용", "처리 여부" };

		String policy[] = { "p_code", "title", "uri", "apply_start", "apply_end", "start_age", "end_age", "contents",
				"application_target", "dor", "si", "crawling_date", "expiration_flag" };
		String policy_change[] = { "\n정책 번호", "정책 제목", "신청 URI", "신청시작", "신청 마감", "최소 연령", "최대 연령", "내용", "정책 대상",
				"시행 지역(대)", "시행 지역(소)", "크롤된 시간", "정책 만료 여부" };
		try {

			URL url = new URL(requestURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			// con.setRequestProperty("Authorization", header);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
			}
			String inputLine;
			int num = 0;
			System.out.println("----------------------------");

			while ((inputLine = br.readLine()) != null) {
				String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s.: @_,]";
				String convert = inputLine.replaceAll(match, "");

				// tuple number
				String key = "}";
				char start_c = key.charAt(0);
				int fromIndex = 0;
				while (true) {
					int index = inputLine.indexOf(start_c, fromIndex);
					if (index == -1)
						break;
					else {
						num++;
						fromIndex = index + 1;
					}
				}

				if (table == "user") // user table일 경우
				{
					for (int i = user.length - 1; i >= 0; i--) {
						convert = convert.replaceAll(user[i], user_change[i]);
					}

				} else if (table == "request") {
					for (int i = request.length - 1; i >= 0; i--) {
						convert = convert.replaceAll(request[i], request_change[i]);
					}
				} else if (table == "policy") {
					for (int i = policy.length - 1; i >= 0; i--) {
						convert = convert.replaceAll(policy[i], policy_change[i]);
					}
				}

				String convertArray[] = convert.split(",");
				int convert_arr_len = convertArray.length;
				int cnt = 0;

				while (cnt < convert_arr_len) {
					System.out.println(convertArray[cnt]);
					cnt++;
				}
				System.out.println("----------------------------");
				System.out.println("검색 된 " + table + ": " + num);

			}
			System.out.println("----------------------------");
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// REST API - POST
	public static void POST(String requestURL, String postParameters) {

		try {

			URL url = new URL(requestURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");

			// post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeUTF(postParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
			}
			String inputLine;

			while ((inputLine = br.readLine()) != null) {

				if (inputLine == "error") {
					System.out.println("SQL error 발생");
				} else {
					System.out.println("정상처리 완료");
				}
			}
			System.out.println("----------------------------");
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
