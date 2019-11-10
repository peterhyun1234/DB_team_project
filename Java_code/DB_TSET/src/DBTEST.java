import java.util.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.DataOutputStream;
import java.net.URL;

// �ڵ�����: ctrl + shift + f
public class DBTEST {

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
		} else if (ID.equals("user") & pswd.equals("user")) // ����� �α���
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
		// TODO Auto-generated method stub
		System.out.println("<1. ȸ�� ��� Ȯ��>");
		
		GET("http://49.236.136.213:3000/user/show_all_users");		
	}

	private static void show_user_interest() {
		// TODO Auto-generated method stub
		System.out.println("<2. ȸ���� ���ɻ� Ȯ��>");
	}

	private static void modify_policy() {
		// TODO Auto-generated method stub
		System.out.println("<3. ��å ���λ��� ����>");
		String uID = scan.nextLine();
		String p_code = scan.nextLine();
		String contents = scan.nextLine();
		
		String urlParameters = "review_uID =" + uID + "&p_code=" + p_code + "&contents=" + contents; 
		POST("http://49.236.136.213:3000/review/write_review", urlParameters);
	}

	private static void show_user_request() {
		// TODO Auto-generated method stub
		System.out.println("<4. ����� ��û Ȯ��>");

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
		// TODO Auto-generated method stub
		System.out.println("<1. ��å Ű���� �˻�>");
	}

	private static void age_based_search() {
		// TODO Auto-generated method stub
		System.out.println("<2. ���� ��� ��å �˻�>");

	}

	private static void location_based_search() {
		// TODO Auto-generated method stub
		System.out.println("<3. ������ ��� ��å �˻�>");

	}
	
	private static void category_based_search() {
		// TODO Auto-generated method stub
		System.out.println("<4. �����о� ��� �˻�>");

	}

	private static void suggest_custom_policy() {
		// TODO Auto-generated method stub
		System.out.println("<5. ����� ���� ��å ��õ>");

	}

	private static void send_request() {
		// TODO Auto-generated method stub
		System.out.println("<6. ���ǻ��� �ۼ�>");

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
			} 
			else if (ID.equals("user") & pswd.equals("user")) // ����� �α���
			{
				user_program();
				break;
			}			
		}
		System.out.println("program�� �����մϴ�.");
		return;
	}
	
	
	// https://developers.naver.com/docs/common/openapiguide/apicall.md <-- �ڹ� REST API ����!! 
	// https://118k.tistory.com/225 <-- �ڹ� REST API ����!! 
	
	//REST API - GET
	public static void GET(String requestURL) {
		
        try {

            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            //con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // ���� ȣ��
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // ���� �߻�
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }
            String inputLine;
            //StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
            	//System.out.println(inputLine);
            	StringTokenizer str = new StringTokenizer(inputLine, ","); //StringTokenizer Ŭ���� ����, �Ľ��Ϸ��� ���ڿ�, ������ "," 
 
            	
            	while (str.hasMoreTokens()) {
            		// https://lifeones.tistory.com/89 <-- �ڹ� �Ľ� ����!! 
            		
            		System.out.println(str.nextToken());
            	}

            }
            br.close();
            //System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
	}
	
	//REST API - POST
	public static void POST(String requestURL, String postParameters) {
		

        try {


            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");

            // post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeUTF(postParameters);
            wr.flush();
            wr.close();
            
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // ���� ȣ��
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // ���� �߻�
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



