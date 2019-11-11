# DB_team_project (2019.11.01 - 2019.12.12)
Title: Policy Referral Service
-------------------------------------
## 1. Start node
### 1.1. install node and npm     
    $ npm init
    $ npm install express mysql --save --save-exact
    
## 2. Mysql
### 2.1. structure
![DB_project](https://user-images.githubusercontent.com/46476398/68565702-aa2efb80-0497-11ea-83d7-30ee7196ffb4.png)
   
## 3. directory description
### 3.1 /Java_code  
    contents about Java application
### 3.2 /config  
    contents about DB
    
## 4. How to use "Java application"
### 4.1 GET Method
```java
	public static void GET(String requestURL) {
            ...
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            //con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }
            ...
	}
```
> URL을 참고해서 파싱하는 구조, 파싱은 개발자 재량
### 4.2 POST Method    
```java
	public static void POST(String requestURL, String postParameters) {
            ...
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            // post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeUTF(postParameters);
            ...
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }
            ...
	}
```
> URL을 참고해서 파싱하는 구조, 파싱은 개발자 재량
> GET과 다른점은 postParameters를 String 형태로 보낸다. 
> ex. 
```java
		String uID = scan.nextLine();
		String p_code = scan.nextLine();
		String contents = scan.nextLine();
		
		String urlParameters = "review_uID =" + uID + "&p_code=" + p_code + "&contents=" + contents; 
		POST("http://49.236.136.213:3000/review/write_review", urlParameters);
```
## 5. Component structures
![component_structure](https://user-images.githubusercontent.com/46476398/68565713-b0bd7300-0497-11ea-87e8-3da3ccea5608.png)

## 6. 
