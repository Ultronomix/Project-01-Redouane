create table reimbursement_types(
                                    type_id varchar primary key,
                                    type varchar unique);

create table user_roles(
                           role_id varchar primary key,
                           role varchar unique);

create table reimbursement_statuses (
                                        status_id varchar primary key,
                                        status varchar unique);


create table users(
                      user_id varchar primary key,
                      username varchar unique not null,
                      email varchar unique not null,
                      password varchar not null,
                      given_name varchar not null,
                      surname varchar not null,
                      is_active boolean,
                      role_id varchar,
                      foreign key (role_id) references user_roles (role_id));

create table reimbursements(
                               reimb_id varchar primary key,
                               amount numeric(6,2) not null,
                               submitted timestamp not null,
                               resolved timestamp,
                               description varchar not null,
                               payment_id varchar,
                               author_id varchar not null ,
                               resolver_id varchar,
                               status_id varchar not null,
                               type_id varchar not null,
                               foreign key (author_id) references users (user_id),
                               foreign key (status_id) references reimbursement_statuses (status_id),
                               foreign key (type_id) references reimbursement_types (type_id));