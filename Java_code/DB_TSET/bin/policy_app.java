import java.util.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.DataOutputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// �ڵ�����: ctrl + shift + f

//������
//1. ȸ�� ��� Ȯ�� (done)
//2. ȸ���� ���ɻ� Ȯ�� (done)
//3. ��å ���λ��� ���� (�ణ �߸ŷ� ����)
//4. ����� ��û Ȯ�� (done)

//�����
//1. ��å Ű���� �˻� (temp/keyword_search)
//2. ���� ��� ��å �˻� (temp/age_search)
//3. ������ ��� ��å �˻� (temp/location_search)
//4. �����о� ��� �˻� (temp/category_search)
//5. ����� ���� ��å ��õ (temp/referral + temp/user_based_referral)
//6. ���ǻ��� �ۼ� (temp/send_req)

public class policy_app {

	static String Input_str;
	static String ID;
	static String pswd;
	static Scanner scan = new Scanner(System.in);// ���� �Է��� ���ڷ� Scanner ����

	public static void main(String[] args) {

		System.out.println("================================");
		System.out.println("<�α���>");
		System.out.println("ID:");
		ID = scan.nextLine();
		System.out.println("PASSWORD:");
		pswd = scan.nextLine();

		if (ID.equals("admin") & pswd.equals("admin")) // ������ �α���
		{
			admin_program();
		} else if (ID.equals("peterhyun1234@gmail.com") & pswd.equals("1234")) // ����� �α���
		{
			user_program();
		} else // �α��� ����
		{
			signin_failure();
		}
	}

	// �����ڿ� ���α׷�
	public static void admin_program() {

		System.out.println("================================");
		System.out.println("<������ ��� ����> \n���α׷��� �����Ͻ÷��� exit�� �Է����ּ���.\n");
		System.out.println("1. ȸ�� ��� Ȯ��");
		System.out.println("2. ȸ���� ���ɻ� Ȯ��");
		System.out.println("3. ��å ���λ��� ����");
		System.out.println("4. ����� ��û Ȯ��");
		System.out.println("================================\n");
		System.out.println("���ϴ� ����� ��ȣ�� �Է����ּ���:");

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
				System.out.println("���ϴ� ����� ��ȣ�� �ٽ� �Է����ּ���:");
				break;
			}
			Input_str = scan.nextLine();
		}
		System.out.println("program�� �����մϴ�.");
		return;
	}

	private static void show_user_list() {

		System.out.println("<1. ȸ�� ��� Ȯ��>");
		GET("http://49.236.136.213:3000/temp/all_users", "user");
	}

	private static void show_user_interest() {

		System.out.println("<2. ȸ���� ���ɻ� Ȯ��>");
		GET("http://49.236.136.213:3000/temp/users_priority", "user");
	}

	private static void modify_policy() {

		System.out.println("<3. ��å ���λ��� ����>");
		System.out.println("������ ���ϴ� ��å ��ȣ:");
		String get_p_code = scan.nextLine();
		get_p_code = "5519";
		System.out.println("----- < ��å ��ȣ: " + get_p_code + " > -----");
		GET("http://49.236.136.213:3000/policy/" + get_p_code, "policy");

		String p_code = "'" + get_p_code + "'";
		String title = "'��⿩�� ���������'";
		String uri = "'https://www.jobaba.net/sprtPlcy/info/view.do?seq=5519'";
		String apply_start = "'2019/12/02 09:00:00'";
		String apply_end = "'2019/12/13 00:00:00'";
		String start_age = "35";
		String end_age = "59";
		String contents = "'������� Ȯ�� �� ���� ��û'";
		String application_target = "'- ����- �� 35��~59��- �������- ���������ҵ� 100%����- ��⵵ 1�� �̻� ������'";
		String dor = "'���'";
		String si = "'��ü'";
		String expiration_flag = "0";

		System.out.println("==============��å����==============");
		System.out.println("���� ������ ��ģ �� 'done'�� �Է����ּ���.\n");
		System.out.println("1. ��å ���� ����");
		System.out.println("2. ��å ���۳�¥ ����");
		System.out.println("3. ��å ���ᳯ¥ ����");
		System.out.println("4. ���� �ּҳ��� ����");
		System.out.println("5. ���� �ִ볪�� ����");
		System.out.println("6. ��å ���� ����");
		System.out.println("7. ���� ��� ���� ����");
		System.out.println("================================\n");

		String input_str = scan.nextLine();

		while (!input_str.equals("done")) {
			switch (input_str) {
			case "1":
				System.out.println("������ ��å ������ �Է����ּ���");
				title = scan.nextLine();
				break;
			case "2":
				System.out.println("������ ��å ���۳�¥�� �Է����ּ���");
				apply_start = scan.nextLine();
				break;
			case "3":
				System.out.println("������ ��å ���ᳯ¥�� �Է����ּ���");
				apply_end = scan.nextLine();
				break;
			case "4":
				System.out.println("������ ���� �ּҳ��̸� �Է����ּ���");
				start_age = scan.nextLine();
				break;
			case "5":
				System.out.println("������ ���� �ִ볪�̸� �Է����ּ���");
				end_age = scan.nextLine();
				break;
			case "6":
				System.out.println("������ ��å ������ �Է����ּ���");
				contents = scan.nextLine();
				break;
			case "7":
				System.out.println("������ ���� ��� ������ �Է����ּ���");
				application_target = scan.nextLine();
				break;
			default:
				System.out.println("������ ���ϴ� ī�װ��� �ٽ� �Է����ּ���");
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

		System.out.println("<4. ����� ��û Ȯ��>");
		GET("http://49.236.136.213:3000/temp/show_all_reqs", "request");
	}

	// ����ڿ� ���α׷�
	public static void user_program() {
		System.out.println("================================");
		System.out.println("<����� ��� ����> \n���α׷��� �����Ͻ÷��� exit�� �Է����ּ���.\n");
		System.out.println("1. ��å Ű���� �˻�");
		System.out.println("2. ���� ��� ��å �˻�");
		System.out.println("3. ������ ��� ��å �˻�");
		System.out.println("4. �����о� ��� �˻�");
		System.out.println("5. ����� ���� ��å ��õ");
		System.out.println("6. ���ǻ��� �ۼ�");
		System.out.println("================================\n");
		System.out.println("���ϴ� ����� ��ȣ�� �Է����ּ���:");
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
				System.out.println("���ϴ� ����� ��ȣ�� �ٽ� �Է����ּ���:");
				break;
			}
			Input_str = scan.nextLine();
		}
		System.out.println("program�� �����մϴ�.");
		return;
	}

	private static void keyword_based_search() {
		String keyword;
		
		System.out.println("<1. ��å Ű���� �˻�>");
		System.out.println("Ű���带 �Է����ּ���");
		keyword = scan.nextLine();

		GET("http://49.236.136.213:3000/temp/keyword_search", "policy");
	}

	private static void age_based_search() {
		// TODO Auto-generated method stub
		System.out.println("<2. ���� ��� ��å �˻�>");
		System.out.println("���̸� �Է����ּ���");
		String age = scan.nextLine();
		
		GET("http://49.236.136.213:3000/temp/age_search", "policy");

	}

	private static void location_based_search() {
		// TODO Auto-generated method stub
		System.out.println("<3. ������ ��� ��å �˻�>");
		System.out.println("�������� �Է����ּ���");
		String location = scan.nextLine();
		
		GET("http://49.236.136.213:3000/temp/location_search", "policy");

	}

	private static void category_based_search() {
		// TODO Auto-generated method stub
		System.out.println("<4. �����о� ��� �˻�>");
		System.out.println("�����о߸� �Է����ּ���");
		String category = scan.nextLine();
		
		GET("http://49.236.136.213:3000/temp/category_search", "policy");

	}

	private static void suggest_custom_policy() {
		// TODO Auto-generated method stub
		System.out.println("<5. ����� ���� ��å ��õ>");
		System.out.println("===========<���� ���� ��å>===========");
		GET("http://49.236.136.213:3000/temp/referral", "policy");
		System.out.println("=======<���� ����� ����� ��ȣ�� ��å>=======");
		GET("http://49.236.136.213:3000/temp/user_based_referral", "policy");

	}

	private static void send_request() {
		// TODO Auto-generated method stub
		System.out.println("<6. ���ǻ��� �ۼ�>");

		String recv_uID = "peterhyun1234@gmail.com";
		String recv_category = "����";
		String recv_contents = "�߽���ϴ� �����մϴ�";
		
		System.out.println("������ ī�װ��� �Է����ּ���");
		recv_category = scan.nextLine();
		System.out.println("������ ������ �Է����ּ���");
		recv_contents = scan.nextLine();
		
		System.out.println("�����Ͻðڽ��ϱ� (��/�ƴϿ�)");		
		String decision = scan.nextLine();
		
		if(decision.equals("��")) {
			String urlParameters = "uID =" + recv_uID + "&category =" + recv_category + "&contents =" + recv_contents;

			System.out.println("urlParameters: " + urlParameters);

			POST("http://49.236.136.213:3000/temp/send_req", urlParameters);	
		}
		else
		{
			System.out.println("���ۿ� �����߽��ϴ�.");	
		}
	}

	// �α��� ����
	public static void signin_failure() {
		while (!ID.equals("exit")) {
			System.out.println("<�α��� ����> ���α׷��� �����Ͻ÷��� exit�� �Է����ּ���.");
			System.out.println("ID:");
			ID = scan.nextLine();
			System.out.println("PASSWORD:");
			pswd = scan.nextLine();
			if (ID.equals("admin") & pswd.equals("admin")) // ������ �α���
			{
				admin_program();
				break;
			} else if (ID.equals("peterhyun1234@gmail.com") & pswd.equals("1234")) // ����� �α���
			{
				user_program();
				break;
			}
		}
		System.out.println("program�� �����մϴ�.");
		return;
	}

	// https://developers.naver.com/docs/common/openapiguide/apicall.md <-- �ڹ� REST
	// API ����!!
	// https://118k.tistory.com/225 <-- �ڹ� REST API ����!!
	// https://highcode.tistory.com/6 <-- �ڹ� regex ����
	// https://itpangpang.xyz/280 <-- �ڹ� regex ����
	// http://egloos.zum.com/js7309/v/11117112 <-- �ڹ� regex ���� (replaceall ���Խ� ���)

	// REST API - GET
	public static void GET(String requestURL, String table) {
		String user[] = { "uID", "name", "dor", "si", "age", "Employment_sup_priority", "Startup_sup_priority",
				"Life_welfare_priority", "Residential_financial_priority", "sex" };
		String user_change[] = { "\n����� ID", "����� �̸�", "����� ��� ����(��)", "����� ��� ����(��)", "����� ����", "��� ���� ��å ��ȣ��",
				"â�� ���� ��å ��ȣ��", "����*��Ȱ ��å ��ȣ��", "�ְ�*���� ��å ��ȣ��", "��" };

		String request[] = { "req_code", "req_uID", "req_category", "req_time", "req_contents", "req_flag" };
		String request_change[] = { "\n��û ��ȣ", "��û�� ID", "��û �з�", "��û �ð�", "��û ����", "ó�� ����" };

		String policy[] = { "p_code", "title", "uri", "apply_start", "apply_end", "start_age", "end_age", "contents",
				"application_target", "dor", "si", "crawling_date", "expiration_flag" };
		String policy_change[] = { "\n��å ��ȣ", "��å ����", "��û URI", "��û����", "��û ����", "�ּ� ����", "�ִ� ����", "����", "��å ���",
				"���� ����(��)", "���� ����(��)", "ũ�ѵ� �ð�", "��å ���� ����" };
		try {

			URL url = new URL(requestURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			// con.setRequestProperty("Authorization", header);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // ���� ȣ��
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			} else { // ���� �߻�
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

				if (table == "user") // user table�� ���
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
				System.out.println("�˻� �� " + table + ": " + num);

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
			if (responseCode == 200) { // ���� ȣ��
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			} else { // ���� �߻�
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
			}
			String inputLine;

			while ((inputLine = br.readLine()) != null) {

				if (inputLine == "error") {
					System.out.println("SQL error �߻�");
				} else {
					System.out.println("����ó�� �Ϸ�");
				}
			}
			System.out.println("----------------------------");
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
