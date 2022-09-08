INSERT INTO user_roles (role)
VALUES ('ADMIN'), ('USER'), ('MANAGER');

INSERT INTO users (given_name, surname, email, username, "password", role_id)
VALUES
    ('Alice', 'Anderson', 'alice.anderson@revature.com', 'aanderson', 'p4$$W0RD', 'b904a168-b5fe-41df-bb61-730a7bb83b86'),
    ('Bob', 'Bailey', 'bob.bailey@revature.com', 'bbailey', 'p4$$W0RD', '531f46a6-3859-44f6-90ad-f45596e025fa'),
    ('Charles', 'Cantrell', 'charles.cantrell@revature.com', 'ccantrell', 'p4$$W0RD', 'd5fad1fc-2a8b-43ba-90f4-0b6741afe447'),
    ('David', 'Daniels', 'david.daniels@revature.com', 'ddaniels', 'p4$$W0RD', 'd5fad1fc-2a8b-43ba-90f4-0b6741afe447'),
    ('Emily', 'Erikson', 'emily.erikson@revature.com', 'eerikson', 'p4$$W0RD', '3cbd7cf4-e0ce-4b70-92ca-3ec53643e563'),
    ('Frank', 'Freeman', 'frank.freeman@revature.com', 'ffreeman', 'p4$$W0RD', '3cbd7cf4-e0ce-4b70-92ca-3ec53643e563'),
    ('Greg', 'Garrett', 'greg.garrett@revature.com', 'ggarett', 'p4$$W0RD', '5a2e0415-ee08-440f-ab8a-778b37ff6874'),
    ('Howard', 'Hayes', 'howard.hayes@revature.com', 'hhayes', 'p4$$W0RD', '5a2e0415-ee08-440f-ab8a-778b37ff6874'),
    ('Tester', 'McTesterson', 'tester.mctesterson@revature.com', 'tester', 'p4$$W0RD', '7a0cecdb-1d9d-4f67-ae91-aee379826ab8');

INSERT INTO projects (name, owner_id)
VALUES ('Taskmaster', 'e07463f9-1d98-4757-a9f3-a2adadf9e001');
