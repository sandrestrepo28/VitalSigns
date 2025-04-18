USE [master]
GO
/****** Object:  Database [VITALS]    Script Date: 4/11/2025 11:48:57 PM ******/
CREATE DATABASE [VITALS]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'VITALS', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\VITALS.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'VITALS_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\VITALS_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [VITALS] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [VITALS].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [VITALS] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [VITALS] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [VITALS] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [VITALS] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [VITALS] SET ARITHABORT OFF 
GO
ALTER DATABASE [VITALS] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [VITALS] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [VITALS] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [VITALS] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [VITALS] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [VITALS] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [VITALS] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [VITALS] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [VITALS] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [VITALS] SET  ENABLE_BROKER 
GO
ALTER DATABASE [VITALS] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [VITALS] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [VITALS] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [VITALS] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [VITALS] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [VITALS] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [VITALS] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [VITALS] SET RECOVERY FULL 
GO
ALTER DATABASE [VITALS] SET  MULTI_USER 
GO
ALTER DATABASE [VITALS] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [VITALS] SET DB_CHAINING OFF 
GO
ALTER DATABASE [VITALS] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [VITALS] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [VITALS] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [VITALS] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'VITALS', N'ON'
GO
ALTER DATABASE [VITALS] SET QUERY_STORE = ON
GO
ALTER DATABASE [VITALS] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [VITALS]
GO
/****** Object:  Table [dbo].[PATIENTS]    Script Date: 4/11/2025 11:48:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PATIENTS](
	[PATIENT_ID] [bigint] NOT NULL,
	[REGISTRATION_DATE] [date] NOT NULL,
	[FIRST_NAME] [nvarchar](100) NOT NULL,
	[LAST_NAME] [nvarchar](100) NOT NULL,
	[DATE_OF_BIRTH] [date] NOT NULL,
	[GENDER] [nvarchar](10) NOT NULL,
	[AGE] [smallint] NOT NULL,
	[BLOOD_TYPE] [nvarchar](5) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[PATIENT_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MEDICAL_ROL]    Script Date: 4/11/2025 11:48:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MEDICAL_ROL](
	[ROLE_ID] [tinyint] NOT NULL,
	[ROLE_NAME] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ROLE_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MEDICAL_STAFF]    Script Date: 4/11/2025 11:48:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MEDICAL_STAFF](
	[STAFF_ID] [bigint] NOT NULL,
	[ROLE_ID] [tinyint] NOT NULL,
	[FIRST_NAME] [nvarchar](100) NOT NULL,
	[LAST_NAME] [nvarchar](100) NOT NULL,
	[EMAIL] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[STAFF_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ASSIGNMENT]    Script Date: 4/11/2025 11:48:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ASSIGNMENT](
	[ASSIGNMENT_ID] [uniqueidentifier] NOT NULL,
	[STAFF_ID] [bigint] NOT NULL,
	[PATIENT_ID] [bigint] NOT NULL,
	[DATE_ASSIGNED] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ASSIGNMENT_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[VITAL]    Script Date: 4/11/2025 11:48:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VITAL](
	[VITAL_ID] [uniqueidentifier] NOT NULL,
	[ASSIGNMENT_ID] [uniqueidentifier] NOT NULL,
	[DATA_CREATED] [datetime] NOT NULL,
	[HEART_RATE] [smallint] NOT NULL,
	[SATURATION] [smallint] NOT NULL,
	[RESPIRATORY_RATE] [smallint] NOT NULL,
	[TEMPERATURE] [smallint] NOT NULL,
	[BLOOD_GLUCOSE] [smallint] NOT NULL,
	[BLOOD_PRESSURE] [nvarchar](10) NOT NULL,
	[NOTE_MEDICAL] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[VITAL_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  UserDefinedFunction [dbo].[patient_vital]    Script Date: 4/11/2025 11:48:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[patient_vital]
(
    @PATIENT_ID BIGINT
)
RETURNS TABLE
AS
RETURN
(
SELECT 
    a.ASSIGNMENT_ID,
    mr.ROLE_NAME,
	a.STAFF_ID,
    CONCAT(ms.FIRST_NAME, ' ' ,ms.LAST_NAME) as STAFF_NAME,
    a.PATIENT_ID,
	CONCAT(p.FIRST_NAME, ' ', p.LAST_NAME) as PATIENT_NAME,
    v.DATA_CREATED,
    v.HEART_RATE as LPM,
    v.SATURATION as SpO2,
    v.RESPIRATORY_RATE as RPM,
    v.TEMPERATURE as C,
    v.BLOOD_GLUCOSE as mg_dL,
    v.BLOOD_PRESSURE as mmHg,
    v.NOTE_MEDICAL as NOTE
FROM 
	MEDICAL_STAFF ms
JOIN
	MEDICAL_ROL mr ON ms.ROLE_ID = mr.ROLE_ID
JOIN
	ASSIGNMENT a ON ms.STAFF_ID = a.STAFF_ID
JOIN 
	PATIENTS p ON a.PATIENT_ID =p.PATIENT_ID
JOIN 
    VITAL v ON a.ASSIGNMENT_ID = v.ASSIGNMENT_ID
WHERE
    a.PATIENT_ID = @PATIENT_ID
);
GO
/****** Object:  UserDefinedFunction [dbo].[assigment_staff]    Script Date: 4/11/2025 11:48:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[assigment_staff]
(
	@STAFF_ID BIGINT,
	@EMAIL NVARCHAR(100)
)
RETURNS TABLE 
AS
RETURN
(
SELECT 
    a.ASSIGNMENT_ID,
    mr.ROLE_NAME,
	a.STAFF_ID,
    CONCAT(ms.FIRST_NAME, ' ' ,ms.LAST_NAME) as STAFF_NAME,
    a.PATIENT_ID,
	CONCAT(p.FIRST_NAME, ' ', p.LAST_NAME) as PATIENT_NAME
FROM 
	MEDICAL_STAFF ms
JOIN
	MEDICAL_ROL mr ON ms.ROLE_ID = mr.ROLE_ID
JOIN
	ASSIGNMENT a ON ms.STAFF_ID = a.STAFF_ID
JOIN 
	PATIENTS p ON a.PATIENT_ID =p.PATIENT_ID
WHERE 
    a.STAFF_ID = @STAFF_ID OR ms.EMAIL = @EMAIL
);
GO
/****** Object:  View [dbo].[STAFF_ROL]    Script Date: 4/11/2025 11:48:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[STAFF_ROL] AS
SELECT 
    mr.ROLE_NAME,
    ms.ROLE_ID,
    ms.STAFF_ID,
    CONCAT(ms.FIRST_NAME, ' ' ,ms.LAST_NAME) as STAFF_NAME,
    ms.EMAIL
FROM 
    MEDICAL_STAFF ms 
JOIN 
    MEDICAL_ROL mr ON mr.ROLE_ID = ms.ROLE_ID
GO
/****** Object:  View [dbo].[ALL_ASSIGNMENT]    Script Date: 4/11/2025 11:48:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[ALL_ASSIGNMENT] AS
SELECT 
    a.ASSIGNMENT_ID,
    mr.ROLE_NAME,
	a.STAFF_ID,
    CONCAT(ms.FIRST_NAME, ' ' ,ms.LAST_NAME) as STAFF_NAME,
    a.PATIENT_ID,
	CONCAT(p.FIRST_NAME, ' ', p.LAST_NAME) as PATIENT_NAME
FROM 
	MEDICAL_STAFF ms
JOIN
	MEDICAL_ROL mr ON ms.ROLE_ID = mr.ROLE_ID
JOIN
	ASSIGNMENT a ON ms.STAFF_ID = a.STAFF_ID
JOIN 
	PATIENTS p ON a.PATIENT_ID =p.PATIENT_ID
GO
/****** Object:  UserDefinedFunction [dbo].[medical_vital]    Script Date: 4/11/2025 11:48:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[medical_vital]
(
    @STAFF_ID BIGINT,
    @EMAIL NVARCHAR(100)
)
RETURNS TABLE
AS
RETURN
(
SELECT 
    a.ASSIGNMENT_ID,
    mr.ROLE_NAME,
	a.STAFF_ID,
    CONCAT(ms.FIRST_NAME, ' ' ,ms.LAST_NAME) as STAFF_NAME,
    a.PATIENT_ID,
	CONCAT(p.FIRST_NAME, ' ', p.LAST_NAME) as PATIENT_NAME,
    v.DATA_CREATED,
    v.HEART_RATE as LPM,
    v.SATURATION as SpO2,
    v.RESPIRATORY_RATE as RPM,
    v.TEMPERATURE as C,
    v.BLOOD_GLUCOSE as mg_dL,
    v.BLOOD_PRESSURE as mmHg,
    v.NOTE_MEDICAL as NOTE
FROM 
	MEDICAL_STAFF ms
JOIN
	MEDICAL_ROL mr ON ms.ROLE_ID = mr.ROLE_ID
JOIN
	ASSIGNMENT a ON ms.STAFF_ID = a.STAFF_ID
JOIN 
	PATIENTS p ON a.PATIENT_ID =p.PATIENT_ID
JOIN 
    VITAL v ON a.ASSIGNMENT_ID = v.ASSIGNMENT_ID
WHERE 
    a.STAFF_ID = @STAFF_ID OR ms.EMAIL = @EMAIL
);
GO
ALTER TABLE [dbo].[ASSIGNMENT] ADD  DEFAULT (newid()) FOR [ASSIGNMENT_ID]
GO
ALTER TABLE [dbo].[ASSIGNMENT] ADD  DEFAULT (getdate()) FOR [DATE_ASSIGNED]
GO
ALTER TABLE [dbo].[PATIENTS] ADD  DEFAULT (getdate()) FOR [REGISTRATION_DATE]
GO
ALTER TABLE [dbo].[VITAL] ADD  DEFAULT (newid()) FOR [VITAL_ID]
GO
ALTER TABLE [dbo].[VITAL] ADD  DEFAULT (getdate()) FOR [DATA_CREATED]
GO
ALTER TABLE [dbo].[ASSIGNMENT]  WITH CHECK ADD  CONSTRAINT [FK_PATIENT_ID] FOREIGN KEY([PATIENT_ID])
REFERENCES [dbo].[PATIENTS] ([PATIENT_ID])
GO
ALTER TABLE [dbo].[ASSIGNMENT] CHECK CONSTRAINT [FK_PATIENT_ID]
GO
ALTER TABLE [dbo].[ASSIGNMENT]  WITH CHECK ADD  CONSTRAINT [FK_STAFF_ID] FOREIGN KEY([STAFF_ID])
REFERENCES [dbo].[MEDICAL_STAFF] ([STAFF_ID])
GO
ALTER TABLE [dbo].[ASSIGNMENT] CHECK CONSTRAINT [FK_STAFF_ID]
GO
ALTER TABLE [dbo].[MEDICAL_STAFF]  WITH CHECK ADD  CONSTRAINT [FK_ROLE_ID] FOREIGN KEY([ROLE_ID])
REFERENCES [dbo].[MEDICAL_ROL] ([ROLE_ID])
GO
ALTER TABLE [dbo].[MEDICAL_STAFF] CHECK CONSTRAINT [FK_ROLE_ID]
GO
ALTER TABLE [dbo].[VITAL]  WITH CHECK ADD  CONSTRAINT [FK_ASSIGNMENT_ID] FOREIGN KEY([ASSIGNMENT_ID])
REFERENCES [dbo].[ASSIGNMENT] ([ASSIGNMENT_ID])
GO
ALTER TABLE [dbo].[VITAL] CHECK CONSTRAINT [FK_ASSIGNMENT_ID]
GO
ALTER TABLE [dbo].[MEDICAL_STAFF]  WITH CHECK ADD CHECK  (([EMAIL] like '%_@__%.__%'))
GO
USE [master]
GO
ALTER DATABASE [VITALS] SET  READ_WRITE 
GO
