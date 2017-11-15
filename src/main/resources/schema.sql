DROP DATABASE IF EXISTS 51alp;
CREATE DATABASE 51alp;
USE 51alp;
DROP TABLE IF EXISTS video;
CREATE TABLE video (
  video_id varchar(255) NOT NULL,
  video_cate_id varchar(255) DEFAULT NULL,
  video_coverurl varchar(255) DEFAULT NULL,
  video_description varchar(255) DEFAULT NULL,
  video_duration varchar(255) DEFAULT NULL,
  video_file_name varchar(255) DEFAULT NULL,
  video_status varchar(255) DEFAULT NULL,
  video_title varchar(255) DEFAULT NULL,
  PRIMARY KEY (video_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;