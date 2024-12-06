/* Password for both users is: 123 */
INSERT INTO users(
    id, dob, email, first_name, last_name, role, password)
VALUES ('0e4faa55-c281-4d55-8994-f77a3955d0d0','1996-06-20', 'admin@admin.com', 'Admin', 'LastName', 'ADMINISTRATOR', '$2a$12$IcFZkRmY0PK.u0fpspHG3OLaVl26aAqe/ZxSsgqeKsDdIIJ2933jm');

INSERT INTO users(
    id, dob, email, first_name, last_name, role, password)
VALUES ('0e4faa55-c281-4d55-8994-f77a3955d0d7','1996-06-20', 'user@user.com', 'User', 'LastName', 'APPLICANT', '$2a$12$IcFZkRmY0PK.u0fpspHG3OLaVl26aAqe/ZxSsgqeKsDdIIJ2933jm');