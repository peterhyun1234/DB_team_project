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
		} else if (ID.equals("user") & pswd.equals("user")) // 사용자 로그인
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
		System.out.println("----- < 정책 번호: " + get_p_code + " > -----");
		GET("http://49.236.136.213:3000/policy/" + get_p_code, "policy");
		System.out.println("----------------------------");
		String p_code = scan.nextLine();
		String title = scan.nextLine();
		String uri = scan.nextLine();
		String apply_start = scan.nextLine();
		String apply_end = scan.nextLine();
		String start_age = scan.nextLine();
		String end_age = scan.nextLine();
		String contents = scan.nextLine();
		String application_target = scan.nextLine();
		String dor = scan.nextLine();
		String si = scan.nextLine();
		String expiration_flag = scan.nextLine();
		// 2019.12.04
		// API done, connection test required
		// user API test required
		String urlParameters = "p_code =" + p_code + "&title=" + title + "&uri=" + uri;
		POST("http://49.236.136.213:3000/policy/modify_policy", urlParameters);
	}

	private static void show_user_request() {

		System.out.println("<4. 사용자 요청 확인>");
		GET("http://49.236.136.213:3000/request/show_all_reqs", "request");
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
		// TODO Auto-generated method stub
		System.out.println("<1. 정책 키워드 검색>");
	}

	private static void age_based_search() {
		// TODO Auto-generated method stub
		System.out.println("<2. 나이 기반 정책 검색>");

	}

	private static void location_based_search() {
		// TODO Auto-generated method stub
		System.out.println("<3. 거주지 기반 정책 검색>");

	}

	private static void category_based_search() {
		// TODO Auto-generated method stub
		System.out.println("<4. 지원분야 기반 검색>");

	}

	private static void suggest_custom_policy() {
		// TODO Auto-generated method stub
		System.out.println("<5. 사용자 맞춤 정책 추천>");

	}

	private static void send_request() {
		// TODO Auto-generated method stub
		System.out.println("<6. 건의사항 작성>");

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
			} else if (ID.equals("user") & pswd.equals("user")) // 사용자 로그인
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
		String user_change[] = { "\n사용자 ID", "사용자 이름", "사용자 사는 지역(대)", "사용자 사는 지역(소)", "사용자 나이", "취업 관련 정책 선호도", "창업 관련 정책 선호도",
				"복지*생활 정책 선호도", "주거*금융 정책 선호도", "성" };
		
		String request[] = { "req_code", "req_uID", "req_category", "req_time", "req_contents", "req_flag"};
		String request_change[] = { "\n요청 번호", "요청자 ID", "요청 분류", "요청 시간", "요청 내용", "처리 여부"};
		
		String policy[] = { "p_code", "title", "uri", "apply_start", "apply_end", "start_age", "end_age", "contents", 
				"application_target", "dor", "si", "expiration_flag"};
		String policy_change[] = { "\n정책 번호", "정책 제목", "신청 URI", "신청시작", "신청 마감", "최소 연령", "최대 연령", "내용", 
				"정책 대상", "시행 지역(대)", "시행 지역(소)", "정책 만료 여부"};
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

			while ((inputLine = br.readLine()) != null) {
				String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s.: @_,]";
				String convert = inputLine.replaceAll(match, "");
				
				
				if (table == "user") // user table일 경우
				{
					for (int i = user.length-1; i >= 0; i--) {
						convert = convert.replaceAll(user[i], user_change[i]);
					}
				}
				else if(table == "request")
				{
					for (int i = request.length-1; i >= 0; i--) {
						convert = convert.replaceAll(request[i], request_change[i]);
					}					
				}
				else if(table == "policy")
				{
					for (int i = policy.length-1; i >= 0; i--) {
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

			}
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

				StringTokenizer str = new StringTokenizer(inputLine, ",");

				while (str.hasMoreTokens()) {
					System.out.println(str.nextToken());
				}

			}
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
