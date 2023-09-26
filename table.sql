CREATE TABLE user_login_info (
	user_id	bigint	NOT NULL	AUTO_INCREMENT,
	user_account 	varchar(15)	NOT NULL,
	user_password	varchar(20) NOT NULL,
	PRIMARY KEY (user_id),
	UNIQUE (user_account)
);

CREATE TABLE user_basic_info (
	user_id		bigint	NOT NULL,
	user_name	varchar(20),
	user_age	int,
	user_gender	varchar(10),
	user_phone	varchar(15),
	user_address	varchar(60),
	PRIMARY KEY (user_id),
	CONSTRAINT id_fk1 FOREIGN KEY (user_id) REFERENCES user_login_info(user_id)
);

CREATE TABLE prize_info (
	prize_id	bigint		NOT NULL AUTO_INCREMENT,
	prize_name	varchar(20)	NOT NULL,
	prize_amount	int		NOT NULL,
	prize_grade	varchar(40)	NOT NULL,
	prize_initiator	bigint	NOT NULL,
	PRIMARY KEY (prize_id),
	CONSTRAINT prize_initiator_id_fk2 FOREIGN KEY (prize_initiator) REFERENCES user_login_info(user_id)
);

CREATE TABLE project_info (
	project_id		bigint			NOT NULL,
	project_name	varchar(20)		NOT NULL,
	project_start_time	datetime	NOT NULL,
	project_end_time	datetime	NOT NULL,
	project_initiator_id	bigint	NOT NULL,
	PRIMARY KEY (project_id),
	CONSTRAINT project_initiator_id_fk3 FOREIGN KEY (project_initiator_id) REFERENCES user_login_info(user_id)
);

CREATE TABLE project_prize_info (
	project_prize_id	bigint	NOT NULL,
	project_id			bigint	NOT NULL,
	prize_id			bigint	NOT NULL,
	PRIMARY KEY (project_prize_id),
	CONSTRAINT project_id_fk4 FOREIGN KEY (project_id) REFERENCES project_info (project_id),
	CONSTRAINT prize_id_fk4 FOREIGN KEY (prize_id) REFERENCES prize_info (prize_id),
	CONSTRAINT project_prize4 UNIQUE (project_id, prize_id)
);

CREATE TABLE participant_info (
	participant_id		bigint	NOT NULL,
	project_id			bigint	NOT NULL,
	user_id				bigint	NOT NULL,
	participant_time	datetime	NOT NULL,
	PRIMARY KEY (participant_id),
	CONSTRAINT project_id_fk5 FOREIGN KEY (project_id) REFERENCES project_info (project_id),
	CONSTRAINT user_id_fk5 FOREIGN KEY (user_id) REFERENCES user_login_info (user_id),
	CONSTRAINT project_user5 UNIQUE (project_id, user_id)
);

--删除
CREATE TABLE winner_info (
	winner_id	bigint	NOT NULL,
	project_id	bigint	NOT NULL,
	user_id		bigint	NOT NULL,
	prize_id	bigint	NOT NULL,
	PRIMARY KEY (winner_id),
	CONSTRAINT project_id_fk6 FOREIGN KEY (project_id)	REFERENCES	project_info (project_id),
	CONSTRAINT user_id_fk6 FOREIGN KEY (user_id) REFERENCES user_login_info (user_id),
	CONSTRAINT prize_id_fk6 FOREIGN KEY (prize_id) REFERENCES prize_info (prize_id),
	CONSTRAINT project_user_prize6 UNIQUE (project_id, user_id, prize_id)
);

CREATE TABLE message_info (
	message_id		bigint 		NOT NULL,
	winner_id		bigint 		NOT NULL,
	message_time	datetime	NOT NULL,
	PRIMARY KEY (message_id),
	CONSTRAINT winner_id FOREIGN KEY (winner_id) REFERENCES winner_info (winner_id)
);



CREATE TABLE winner_info (
     winner_id	bigint	NOT NULL AUTO_INCREMENT,
     user_id	bigint	NOT NULL,
     prize_id	bigint	NOT NULL,
     PRIMARY KEY (winner_id),
     CONSTRAINT user_id_fk6 FOREIGN KEY (user_id) REFERENCES user_login_info (user_id),
     CONSTRAINT prize_id_fk6 FOREIGN KEY (prize_id) REFERENCES prize_info (prize_id),
     CONSTRAINT project_user_prize6 UNIQUE (user_id, prize_id)
);
