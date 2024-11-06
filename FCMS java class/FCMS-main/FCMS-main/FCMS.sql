1
CREATE DATABASE FCMS

drop table DBLogin

CREATE TABLE DBLogin
(
	UserID INT IDENTITY(1,1) PRIMARY KEY,
	UserName VARCHAR(20),
	UserPassword VARCHAR(10)
)

SELECT * FROM DBLogin

INSERT INTO DBLogin(UserName,UserPassword)
VALUES('Admin','Admin'),
		('hung','hungminh')

CREATE TABLE Team
(
	Team_ID INT IDENTITY(40,1) PRIMARY KEY,
	Team_Name VARCHAR (20)
)


------------------------------------------------------------------------
ALTER TABLE SELL_TABLE
DROP CONSTRAINT PK__SELL_TAB__ED9D75A933DA7591;
---------------------------------------------------------------------------


SELECT * FROM Team
DROP Table Team

INSERT INTO Team(Team_Name)
VALUES ('A'), ('B'), ('C');


DROP Table Player
CREATE TABLE Player
(
	P_ID INT IDENTITY(100,1) PRIMARY KEY,
	P_Name VARCHAR(20),
	P_Pos VARCHAR(5),
	P_Age NUMERIC(2),
	P_SEX VARCHAR(1),
	P_Weight INT,
	P_Height INT,
	P_Nation VARCHAR(15),
	P_Rating NUMERIC(3,2),
	P_Medical_Condition VARCHAR(10),
	P_Salary DECIMAL (18,2),
	P_Performance_Bonus DECIMAL(5,2) NULL,
	P_LEAVE INT NULL,
	P_Description VARCHAR(50),
	Team_ID INT FOREIGN KEY REFERENCES Team(Team_ID)
)

INSERT INTO Player(P_Name,P_Pos,P_Age,P_Sex,P_Weight,P_Height,P_Nation,P_Rating,P_Medical_Condition,P_Salary,P_Performance_Bonus,P_Description,Team_ID)
VALUES('Cristiano Ronaldo','FW',36,'M',83,187,'Portuguese',7.45,'Good',9176208.62,5,'Best Player',40)

INSERT INTO Player(P_Name,P_Pos,P_Age,P_Sex,P_Weight,P_Height,P_Nation,P_Rating,P_Medical_Condition,P_Salary,P_Performance_Bonus,P_Description,Team_ID)
VALUES('Ter Stegen','GK',28,'M',85,189,'German',8.9,'Well',4800000,5,'Good Goalkeeper',40)

INSERT INTO Player(P_Name,P_Pos,P_Age,P_Sex,P_Weight,P_Height,P_Nation,P_Rating,P_Medical_Condition,P_Salary,P_Performance_Bonus,P_Description,Team_ID)
VALUES('Neto','GK',32,'M',83,191,'Brazil',6.5,'Good',3120000,2,'Average Goalkeeper',41),
	  ('Inaki Pena','GK',22,'M',22,184,'Spanish',6.4,'Good',1500000,1,'Try More',42),
	  ('Arnau Tenas','GK',29,'M',78,185,'Spanish',7.0,'Good',720000,2,'Try More',42),
	  ('Gerard Pique','DEF',34,'M',85,194,'Spanish',8.0,'Good',120000000,1,'Good Defender',40),
	  ('Jordi Alba','DEF',32,'M',68,170,'Spanish',7.5,'Good',7200000,3,'Average Defender',41),
	  ('Clement Lenget','DEF',26,'M',80,185,'France',6.0,'Good',3120000,2,'Average Defender',41),
	  ('Samuel Umtiti','DEF',27,'M',84,184,'France',7.9,'Good',10000000,2,'Good Defender',40),
	  ('Eric Garcia','DEF',20,'M',73,183,'Spanish',6.0,'Good',2000000,2,'Try More',40),
	  ('Sergio Busquets','M',33,'M',78,189,'Spanish',5.5,'Good',1240000,2,'Average Midfielder',40),
	  ('Pedri','M',18,'M',64,174,'Spanish',5.5,'Good',1240000,2,'Average Midfielder',40)

SELECT * FROM Player

DROP TABLE COACH
CREATE TABLE Coach
(
	C_ID INT IDENTITY(1000,1) PRIMARY KEY,
	C_Name VARCHAR(30),
	C_Formation VARCHAR(30),
	C_Designation VARCHAR(30),
	C_Age INT,
	C_Sex VARCHAR(7),
	C_Medical_Condition VARCHAR(50),
	C_Salary DECIMAL(10,2),
	C_Performance_Bonus  DECIMAL(10,2) NULL ,
	C_Description VARCHAR(50),
	Team_ID INT FOREIGN KEY REFERENCES Team(Team_ID)
)

SELECT * FROM Coach

INSERT INTO Coach(C_Name,C_Formation, C_Designation,C_Age,C_Sex,C_Medical_Condition,C_Salary,C_Performance_Bonus,C_Description, Team_ID)
VALUES('Herik Larsson','5-3-2','Assistant',50,'M','Good',300000,1500,'Alright',41)


INSERT INTO Coach(C_Name,C_Formation,C_Designation,C_Age,C_Sex,C_Medical_Condition,C_Salary,C_Performance_Bonus,C_Description,Team_ID)
VALUES('Ronald Koeman','4-3-3','Head',58,'M','Good',4500000,5000,'Alright',40)

INSERT INTO Coach(C_Name,C_Formation,C_Designation,C_Age,C_Sex,C_Medical_Condition,C_Salary,C_Performance_Bonus,C_Description,Team_ID)
VALUES('Herik Larsson','5-3-2','Assistant',50,'M','Good',300000,1500,'Alright',41)

INSERT INTO Coach(C_Name,C_Formation,C_Designation,C_Age,C_Sex,C_Medical_Condition,C_Salary,C_Performance_Bonus,C_Description,Team_ID)
VALUES('Alfred Schreuder','4-2-4','Assistant',48,'M','Good',220000,1755,'Alright',42)

INSERT INTO Coach(C_Name,C_Formation,C_Designation,C_Age,C_Sex,C_Medical_Condition,C_Salary,C_Performance_Bonus,C_Description,Team_ID)
VALUES('Jose De La Fuente','3-4-3','GK Coach',50,'M','Good',350000,2000,'Alright',40)

INSERT INTO Coach(C_Name,C_Formation,C_Designation,C_Age,C_Sex,C_Medical_Condition,C_Salary,C_Performance_Bonus,C_Description,Team_ID)
VALUES('Albert Roca','4-4-2','Fitness',58,'M','Good',250000,0500,'Alright',40)



drop table staff
CREATE TABLE Staff
(
	S_ID INT IDENTITY(2000,1) PRIMARY KEY,
	S_Name VARCHAR(30),
	S_Type VARCHAR(30),
	S_Age INT,
	S_Sex VARCHAR(7),
	S_Medical_Condition VARCHAR(50),
	S_Salary DECIMAL(10,2),
	S_Contract_Year INT,
	S_Release_Date VARCHAR(30),
	S_InLeave INT NULL,
	Team_ID INT FOREIGN KEY REFERENCES Team(Team_ID)
)

SELECT * FROM Staff

INSERT INTO Staff(S_Name,S_Type,S_Age,S_Sex,S_Medical_Condition,S_Salary,S_Contract_Year,S_Release_Date,S_InLeave)
VALUES()

INSERT INTO Staff(S_Name,S_Type,S_Age,S_Sex,S_Medical_Condition,S_Salary,S_Contract_Year,S_Release_Date,S_InLeave)
VALUES('Ramon Planes','Sporting Director',53,'M','Covid',400000,5,'2026-05-13',14)

INSERT INTO Staff(S_Name,S_Type,S_Age,S_Sex,S_Medical_Condition,S_Salary,S_Contract_Year,S_Release_Date,S_InLeave)
VALUES('Ramon Planes','Sporting Director',53,'M','Covid',400000,5,'2026-05-13',14)

INSERT INTO Staff(S_Name,S_Type,S_Age,S_Sex,S_Medical_Condition,S_Salary,S_Contract_Year,S_Release_Date,S_InLeave)
VALUES('Jordy Cruyff','Adviser',47,'M','Alright',700000,2,'2023-05-09',0)

INSERT INTO Staff(S_Name,S_Type,S_Age,S_Sex,S_Medical_Condition,S_Salary,S_Contract_Year,S_Release_Date,S_InLeave)
VALUES('Jordi Cardoner','Vice-President',59,'M','Covid',2500000,6,'2027-05-13',0)

INSERT INTO Staff(S_Name,S_Type,S_Age,S_Sex,S_Medical_Condition,S_Salary,S_Contract_Year,S_Release_Date,S_InLeave)
VALUES('Mari Bakero','Scouting Head',58,'M','Covid',200000,5,'2026-05-13',14)

INSERT INTO Staff(S_Name,S_Type,S_Age,S_Sex,S_Medical_Condition,S_Salary,S_Contract_Year,S_Release_Date,S_InLeave,Team_ID)
VALUES('Jodi Melero','Scout',49,'M','Alright',100000,6,'2027-02-13',0,40),
	  ('Carles Rexach','Scout',74,'M','Sick',50000,6,'2027-02-23',10,41),
	  ('Joseph Moratalla','Scout',62,'M','Alright',100000,8,'2029-12-13',0,42)




drop table Tournament
CREATE TABLE Tournament
(
    Tm_ID INT IDENTITY(1800,1) PRIMARY KEY,
    Tm_Match_Type VARCHAR(20),
    Tm_Stadium VARCHAR(20),
    Tm_Match_Area VARCHAR(10),
    Tm_Date VARCHAR(11),
    Tm_Opponent VARCHAR(20),
    Tm_Result VARCHAR(4) NULL,
    Tm_Match_Fee Decimal(16,2),
    Team_ID INT FOREIGN KEY REFERENCES Team(Team_ID)
)
INSERT INTO Tournament(Tm_Match_Type, Tm_Stadium, Tm_Match_Area, Tm_Date, Tm_Opponent, Tm_Result, Tm_Match_Fee,Team_ID)
VALUES('La Liga', 'Camp Nou', 'Home','2021-08-04', 'Paris SG' ,'Won', 50000, 40),
      ('La Liga', 'Estadio Mendizorrota', 'Away', '2021-08-08', 'Real Madrid' ,'Loss', 10000, 41),
      ('Champions League', 'Los Carmenes Stadium', 'Away', '2021-09-10', 'Arsenal ' ,'Won', 60000, 40),
      ('Champions League', 'Los Carmenes Stadium', 'Home', '2021-09-20', 'Real Madrid' ,'Draw', 20000, 40)

SELECT * FROM Tournament

SELECT * FROM Tournament WHERE Tm_Date BETWEEN '2021-08-01' and '2021-10-31'

SELECT * FROM Tournament WHERE Tm_Date BETWEEN '2021-09-15' AND'2021-09-30'


drop table GroundandGym

CREATE TABLE GroundandGym
(
	GG_No INT IDENTITY (500,1) PRIMARY KEY,
	GG_Name VARCHAR(20),
	GG_Type VARCHAR(10),
	GG_Capacity NUMERIC(10),
	GG_Description VARCHAR(10),
	S_ID INT FOREIGN KEY REFERENCES Staff(S_ID)
)

--INSERT INTO Coach(C_Name,C_Designation,C_Age,C_Sex,C_Medical_Condition,C_Salary,C_Contract_Year,C_Performance_Bonus,C_Release_Date,C_Description,Team_ID)
--VALUES()

--INSERT INTO Tournament(Tm_Match_Type,Tm_Match_Area,Tm_Stadium,Tm_Result,Tm_Opponent,Tm_Win_Ratio,Tm_Match_Fee,Team_ID)
--VALUES()

--INSERT INTO Media(Me_Type,Me_Contract_Year,Me_Revenue,Me_Title,Tournament_ID)
--VALUES()

--INSERT INTO GroundandGym(GG_Name,GG_Type,GG_Capacity,GG_Description,Staff_ID)
--VALUES()

INSERT INTO GroundandGym(GG_Name,GG_Type,GG_Capacity,GG_Description,S_ID)
VALUES('Mini Estadi','Training',15276,'Alright',2005),
	  ('Camp nou gym','Gym',56,'Good',2006),
	  ('Narcis Sala Gym','Gym',45,'Good',2007)

select * from GroundandGym gg join Staff s on s.S_ID=gg.S_ID AND gg.GG_No= 500

SELECT * FROM BUYTABLE
DELETE FROM Coach

SELECT * FROM Player P JOIN SELL_TABLE S
	   ON P.P_ID = S.SELL_ID WHERE P_Pos LIKE '%LCB%' AND ( SELL_MVA BETWEEN 1000 AND 100000 )

SELECT * FROM Coach WHERE C_ID LIKE '%H%' OR C_Name LIKE '%H%' OR C_Formation LIKE '%H%' OR C_Designation LIKE '%H%' OR C_Age LIKE '%H%' OR C_Sex LIKE '%H%' OR C_Medical_Condition LIKE '%H%' OR C_Salary LIKE '%H%'


SELECT * FROM Player

SELECT * FROM Coach

SELECT * FROM Staff

--SELECT * FROM Media

SELECT * FROM GroundandGym

DROP TABLE BUYTABLE

CREATE TABLE BUYTABLE(
	TSP_ID INT IDENTITY(20000,1) PRIMARY KEY,
	TSP_NAME VARCHAR(20),
	TSP_AGE NUMERIC(2),
	TSP_HEIGHT INT,
	TSP_NATIONALITY VARCHAR(15),
	TSP_POSITION VARCHAR(5),
	TSP_MVA DECIMAL(11,2),
	TSP_CAPS INT,
	TSP_GOALS INT,
	TSP_CURRENT_CLUB VARCHAR(50),
	TSP_TRANSFERFEE  DECIMAL(18,2)
)


INSERT INTO BUYTABLE(TSP_NAME, TSP_AGE, TSP_HEIGHT, TSP_NATIONALITY, TSP_POSITION, TSP_MVA, TSP_CAPS, TSP_GOALS, TSP_CURRENT_CLUB, TSP_TRANSFERFEE)
VALUES('Neymar', 29, 175, 'Brazil', 'LW', 100000000, 113, 69, 'Paris SG', 222000000),
	  ('Kylian Mbappe', 22, 178, 'France', 'CF', 120000000, 49, 17, 'Paris SG', 145000000),
      ('Gabriel Jesus', 29, 172, 'Brazil', 'AM', 90000000, 63, 18, 'Man City', 145000000),
	  ('NGolo Kante', 24, 178, 'France', 'RW', 50000000, 27, 4, 'Chelsea', 135000000),
      ('Jack Grealish', 26, 180, 'England', 'LW', 65000000, 15, 0, 'Man City', 117050000),
      ('Romelu Lukaku', 28, 191, 'Belgium', 'CF', 100000000, 100, 67, 'Chelsea', 115000000),
	  ('Gareth Bale', 32, 185, 'Wales', 'RW', 65000000, 99, 36, 'Real Madrid', 101000000),
	  ('Jadon Sancho', 21, 180, 'England', 'LW', 100000000, 22, 3, 'Man Utd', 85000000),
	  ('Lion Bailey', 24, 178, 'Jamaica', 'LW', 35000000, 10, 1, 'Real Madrid', 32000000)

SELECT * FROM BUYTABLE

-- Single query

SELECT * FROM BUYTABLE WHERE TSP_NAME LIKE '%le%'

SELECT * FROM BUYTABLE WHERE TSP_ID IN (20000,20002)

SELECT * FROM BUYTABLE WHERE TSP_CURRENT_CLUB IN ('Real Madrid', 'Man City')

SELECT * FROM BUYTABLE WHERE TSP_ID BETWEEN 20000 AND 20006

-- Query with nested condition
SELECT * FROM BUYTABLE WHERE ( TSP_ID BETWEEN 20000 AND 20006 ) AND ( TSP_CAPS >= ( SELECT TSP_CAPS = AVG(TSP_CAPS) FROM BUYTABLE))



SELECT * FROM BUYTABLE WHERE TSP_POSITION = 'LW' 

SELECT * FROM BUYTABLE WHERE TSP_POSITION = 'LW' AND ((TSP_MVA >= 50000000) OR TSP_TRANSFERFEE<=100000000)   

SELECT * FROM BUYTABLE WHERE TSP_POSITION = 'LW' AND ( TSP_AGE BETWEEN 20 AND 26 ) ORDER BY TSP_NAME

SELECT * FROM BUYTABLE WHERE TSP_POSITION = 'LW' AND ( (TSP_AGE BETWEEN 20 AND 26) AND TSP_TRANSFERFEE<=100000000) 

SELECT TOP 3 * FROM BUYTABLE WHERE TSP_POSITION IN('LW','CF') 

SELECT TSP_POSITION,MAX(TSP_MVA) FROM BUYTABLE GROUP BY TSP_POSITION

SELECT * FROM BUYTABLE WHERE TSP_GOALS >(SELECT AVG(TSP_GOALS) FROM BUYTABLE)

SELECT * FROM BUYTABLE WHERE TSP_MVA = (SELECT MAX(TSP_MVA) FROM BUYTABLE) -- Most valuable player according to Market value

SELECT * FROM BUYTABLE WHERE TSP_TRANSFERFEE = (SELECT MAX(TSP_TRANSFERFEE) FROM BUYTABLE) -- Player with highest transfer fee


SELECT * FROM BUYTABLE WHERE TSP_ID = 20000

SELECT * FROM BUYTABLE WHERE TSP_CURRENT_CLUB IN ('', 28)

SELECT * FROM BUYTABLE WHERE TSP_HEIGHT BETWEEN 170 AND 180


SELECT TOP 3 * FROM BUYTABLE 

DROP TABLE BUYTABLE



CREATE TABLE SELL_TABLE
(
	SELL_ID INT FOREIGN KEY REFERENCES Player(P_ID) PRIMARY KEY,
	SELL_MVA DECIMAL(11,2), 
	SELL_TRANSFERFEE DECIMAL(11,2),
	SELL_CAPS INT,
	SELL_GOAL INT
)

INSERT INTO SELL_TABLE(SELL_ID, SELL_MVA, SELL_CAPS, SELL_GOAL, SELL_TRANSFERFEE)
VALUES(100,100000000, 180, 111, 245000000),
	  (105, 10000000, 103, 5,10050000),
	  (106, 10000000, 79, 9,14010000),
	  (111,  7020000, 10, 0,20000000),
	  (109, 20000000, 13, 0,1070000)

SELECT * FROM SELL_TABLE
DROP TABLE SELL_TABLE

SELECT * FROM Player P JOIN SELL_TABLE S
	   ON P.P_ID = S.SELL_ID 
	   ORDER BY S.SELL_ID







SELECT P_ID, P_name, P_Pos, P_Age, SELL_MVA, SELL_CAPS, SELL_GOAL, SELL_TRANSFERFEE
	   FROM Player P JOIN SELL_TABLE S
	   ON P.P_ID = S.SELL_ID 
	   WHERE SELL_CAPS BETWEEN 50 AND 100 


-- OWN CLUB'S PLAYER WHOOSE MVA >= 
SELECT P_ID, P_name, P_Pos, SELL_CAPS  
	   FROM Player P RIGHT OUTER JOIN SELL_TABLE S
	   ON P.P_ID = S.SELL_ID
	   WHERE SELL_MVA >= (SELECT AVG(SELL_MVA)/2 FROM SELL_TABLE)
SELECT * FROM Tournament tr JOIN Team N tr.Team_ID = t.Team_ID AND Tm_ID=1802

SELECT P_ID, P_name, P_Pos, SELL_MVA, SELL_TRANSFERFEE  
	   FROM Player P RIGHT OUTER JOIN SELL_TABLE S
	   ON P.P_ID = S.SELL_ID
	   WHERE SELL_GOAL <= (SELECT AVG(SELL_GOAL)/2 FROM SELL_TABLE)



 --INSERT INTO Coach(C_Name,C_Formation,C_Designation,C_Age,C_Sex,C_Medical_Condition,C_Salary,C_Contract_Year,C_Performance_Bonus,C_Description,Team_ID)VALUES('S','4-3-3','Head',22,'M','G',1000000,5,'F',40)