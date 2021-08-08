SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE `User`;
TRUNCATE `Job`;
TRUNCATE `Department`;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `Job` (name) VALUES ("개발자"), ("디자이너"), ("기획자"), ("QA");
INSERT INTO `Department` (name) VALUES ("서비스"), ("마케팅"), ("경험 개발");

INSERT INTO `User` (email, name, description, point, status) VALUES
                                                                    ("test1@naver.com", "test1", "test", 0, "ACTIVE"),
                                                                    ("test2@naver.com", "test2", "test", 0, "ACTIVE"),
                                                                    ("test3@naver.com", "test3", "test", 0, "ACTIVE")
