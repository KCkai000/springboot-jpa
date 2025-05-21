package com.example.demo.model.entity;

import org.hibernate.annotations.Collate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 
Entity name:User
Table name: users
+---------+-----------+--------------+------+-------+--------+------+
| user_id | username | password_hash | salt | email | active | role |
+---------+-----------+--------------+------+-------+--------+------+
|    1    |     KK    | Qwe48854...  | $Qf..| A@... |  true  | admin|
+---------+-----------+--------------+------+-------+--------+------+
role => 權限 ;
*/


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //實體類與資料表對應(會自動建立資料表SQL)
@Table(name ="users")  //可選：可以手動建立資料表名，預設是 class 名稱
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //自動生成 id
	@Column(name="user_id")  //預設的資料表中的欄位名稱
	private Integer userId;  //使用者 ID
	
	@Column(name="username", unique = true, nullable = false, length =50)  //帳號有唯一性，但密碼沒有xD
	private String username;
	@Column(name="password_hash", nullable = false)
	private String passwordHash;
	@Column(name="salt", nullable = false)
	private String salt;
	@Column(name="email", nullable = false)
	private String email;
	@Column(name="active")
	private Boolean active;
	@Column(name="role")
	private String role;
}
