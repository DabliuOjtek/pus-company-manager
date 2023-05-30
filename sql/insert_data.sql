INSERT INTO users (email, active, password, first_name, last_name, country, city, street, zip_code)
VALUES ('jane@example.com', false, '$2a$12$yTM72UvipzIU6U37BA1ijOT2fSmUssU85Q/CHx5EnhAxRogHwkKeu', 'Jane', 'Doe', 'US', 'New York', 'Broadway', '10001');

INSERT INTO users (email, active, password, first_name, last_name, country, city, street, zip_code)
VALUES ('jim@example.com', false, '$2a$12$yTM72UvipzIU6U37BA1ijOT2fSmUssU85Q/CHx5EnhAxRogHwkKeu', 'Jim', 'Smith', 'CA', 'Los Angeles', 'Sunset Blvd', '90001');

INSERT INTO users (email, active, password, first_name, last_name, country, city, street, zip_code)
VALUES ('sarah@example.com', false, '$2a$12$yTM72UvipzIU6U37BA1ijOT2fSmUssU85Q/CHx5EnhAxRogHwkKeu', 'Sarah', 'Lee', 'CA', 'San Diego', 'Main St', '92093');

INSERT INTO users (email, active, password, first_name, last_name, country, city, street, zip_code)
VALUES ('john.doe@example.com', true, '$2a$12$yTM72UvipzIU6U37BA1ijOT2fSmUssU85Q/CHx5EnhAxRogHwkKeu', 'John', 'Doe', 'United States', 'New York', '123 Main St', '10001');

INSERT INTO users (email, active, password, first_name, last_name, country, city, street, zip_code)
VALUES ('jane.smith@example.com', true, '$2a$12$yTM72UvipzIU6U37BA1ijOT2fSmUssU85Q/CHx5EnhAxRogHwkKeu', 'Jane', 'Smith', 'United States', 'Los Angeles', '456 Elm St', '90001');

INSERT INTO confirmations (user_id, confirm_code, expire_date)
VALUES (1, 'ABCD1234', '2023-06-01 12:00:00');

INSERT INTO confirmations (user_id, confirm_code, expire_date)
VALUES (2, 'EFGH5678', '2023-06-05 18:00:00');

INSERT INTO refresh_tokens (uuid, user_id)
VALUES ('1234567890', 1);

INSERT INTO refresh_tokens (uuid, user_id)
VALUES ('0987654321', 2);

INSERT INTO projects (name, description, start_date, completion_date)
VALUES ('Project A', 'This is project A', '2023-05-30 09:00:00', '2023-06-15 17:00:00');

INSERT INTO projects (name, description, start_date, completion_date)
VALUES ('Project B', 'This is project B', '2023-06-01 10:00:00', '2023-06-30 16:00:00');

INSERT INTO tasks (project_id, name, description, priority, start_date, estimated_completion_date)
VALUES (1, 'Task 1', 'Do task 1', 1, '2023-05-30 09:00:00', '2023-05-31 17:00:00');

INSERT INTO tasks (project_id, name, description, priority, start_date, estimated_completion_date)
VALUES (1, 'Task 2', 'Do task 2', 2, '2023-06-01 10:00:00', '2023-06-02 16:00:00');

INSERT INTO members (is_owner, user_id, project_id)
VALUES (true, 1, 1);

INSERT INTO members (is_owner, user_id, project_id)
VALUES (false, 2, 1);

INSERT INTO comments (comment, member_id, task_id)
VALUES ('This is a comment.', 1, 1);

INSERT INTO comments (comment, member_id, task_id)
VALUES ('Another comment here.', 2, 1);
