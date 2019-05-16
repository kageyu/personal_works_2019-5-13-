package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBhelper {

	//各種フィールド
	static Connection con;
	static Statement st;
	static ResultSet result;

	//各種フィールドを初期化
	public DBhelper(){
		con = null;
		st = null;
		result = null;
	}

	//DB接続をテストするためmainメソッドを仮作成した
	public static void main(String[] args) {

    	String databasename = "battlearena";
    	String user = "battlearena";
    	String password = "12345";
    	String url = "jdbc:mysql://localhost:3306/" + databasename + "?serverTimezone=JST";
    	Connection con = null;

    	System.out.println("DB処理開始前です！");

    	//DB開始処理
    	try {
//    		Class.forName("con.mysql.jdbc.Driver");

    		System.out.println("ClassFound!!");

    		con = DriverManager.getConnection(url,user,password);
//    		String url2 = "jdbc:mysql://localhost:3306/ DBNAME ?"+"user=USER_ID&password=PASSWORD";
    		System.out.println("Connected....");

    		Statement st = con.createStatement();
    		String sqlStr = "SELECT mailaddress FROM user ";
    		ResultSet result = st.executeQuery( sqlStr );

    		System.out.println("データベース接続成功！");

//    		while(result.next()) {
//    			String str1 = result.getString("mailaddress");
//    			String str2 = result.getString("password");
//    			System.out.println( str1 + " , " + str2 );
//    		}

    		//DB終了処理
    		result.close();
    		st.close();
    		con.close();

    	} catch ( SQLException e ) {
    		System.out.println( "Connection Faild. :" + e.toString() );
    		//throw new Exception();

//    	} catch (ClassNotFoundException e) {
//    		System.out.println("ドライバを読み込めませんでした" + e);

    	} finally {
    		try {
    			if(con!=null) {
    				con.close();
    			}
    		}catch ( Exception e ) {
    			System.out.println( "Exception2! :" + e.toString());
    			//throw new Exception();
    		}

    	}

	}

	/**
	 *DB接続処理
	 */
	public static void openDB() {
		String databasename = "battlearena";
		String user = "battlearena";
		String DBpassword = "12345";
		String url = "jdbc:mysql://localhost:3306/" + databasename	+ "?serverTimezone=JST";

		try {
    		System.out.println("ClassFound!!");
    		con = DriverManager.getConnection(url,user,DBpassword);
    		System.out.println( "Connected...." ) ;
    		st = con.createStatement();

    	} catch ( SQLException e ) {
    		System.out.println( "Connection Faild. :" + e.toString() );
    	} finally {
    		try {
    			if(con!=null) {
    				con.close();
    			}
    		}catch ( Exception e ) {
    			System.out.println( "Exception! :" + e.toString());
    		}
    	}
	}

	/**
	 * DB終了処理
	 */
	public static void closeDB() {
		try {
			result.close();
			st.close();
			con.close();
			System.out.println("データベースへの接続を終了しました。");
		} catch ( SQLException e ) {
			System.out.println( "Connection Faild. :" + e.toString() );
		} finally {
			try {
				if(con!=null) {
					con.close();
				}
			}catch ( Exception e ) {
				System.out.println( "Exception! :" + e.toString());
			}
		}
	}


	/**
	 * ログインメールアドレスとパスワードがデータベースに存在するか調べるメソッド
	 * @param mailaddress ユーザーが入力したアドレス
	 * @param password ユーザーが入力したパスワード
	 * @return ログイン情報がデータベース上のユーザー情報と一致した場合はtrue、それ他の場合はfalse
	 */
 	public boolean AuthDB(String mailaddress, String password) {

    	String databasename = "battlearena";
    	String user = "battlearena";
    	String DBpassword = "12345";
    	String url = "jdbc:mysql://localhost:3306/" + databasename	+ "?serverTimezone=JST";

    	int AuthFrag = 0;

    	System.out.println("DB処理開始前です！");

    	//DB開始処理
    	try {
//    		Class.forName("con.mysql.jdbc.Driver");

    		System.out.println("ClassFound!!");

//    		try {
//    			con = DriverManager.getConnection(url,user,DBpassword);
//    			con.close();
//    		}catch( SQLException e ){
//
//        		System.out.println( "1回目のDBアクセスに失敗しました。2回目を実施します。" );
//    			con.close();
//    		}
//    		System.out.println("2回目のDBアクセスを実施します。");

    		con = DriverManager.getConnection(url,user,DBpassword);
    		System.out.println( "Connected...." ) ;

    		st = con.createStatement();

    		String sqlStr = "SELECT password FROM user WHERE mailaddress = '" + mailaddress + "'" ;
    		result = st.executeQuery( sqlStr );

    		System.out.println( "データベース接続成功！" );

    		while(result.next()) {
    			String str1 = result.getString( "password" );
    			System.out.println( password );

    			if ( str1.equals( password ) ) {
    				System.out.println("ユーザー認証に成功しました。");
    				AuthFrag = 1;

    			}else if ( !str1.equals( "" ) ) {
    				System.out.println("パスワードが間違っています。");
    				AuthFrag = 2;

    				//パスワード間違いのケースを実装するまでは認証失敗として扱う
    				AuthFrag = 0;
    			}else {
    	    		System.out.println("ユーザー認証に失敗しました。");
    			}

    			System.out.println(str1);
    		}



    		//DB終了処理
    		result.close();
    		st.close();
    		con.close();
    		System.out.println("データベースへの接続を終了しました。");


    	} catch ( SQLException e ) {
    		System.out.println( "Connection Faild. :" + e.toString() );
    		//throw new Exception();

//    	} catch (ClassNotFoundException e) {
//    		System.out.println("ドライバを読み込めませんでした" + e);

    	} finally {
    		try {
    			if(con!=null) {
    				con.close();
    			}
    		}catch ( Exception e ) {
    			System.out.println( "Exception2! :" + e.toString());
    			//throw new Exception();
    		}
    	}

    	//一連の処理の後、戻り値を反映
		if( AuthFrag == 1) {
			return true;
		}else {
			return false;
		}

	}

	/**
	 * 新規ユーザー登録メソッド
	 * 入力されたアドレスが既に登録済でなければ入力されたパスワードと共にデータベースに登録する
	 * @param mailaddress ユーザーが入力したメールアドレス
	 * @param password パスワード
	 * @return 新規ユーザー登録に成功した場合はtrue、失敗した場合はfalse
	 */
	public boolean insertDB(String mailaddress, String password) {
		return true;
	}

}
