/* Switch to the classicmodels database */
USE classicmodels;

/* Create the tables */
CREATE TABLE productlines (
                              product_line varchar(50),
                              text_description varchar(4000) DEFAULT NULL,
                              html_description mediumtext,
                              image mediumblob,
                              PRIMARY KEY (product_line)
);

CREATE TABLE products (
                          product_code varchar(15),
                          product_name varchar(70) NOT NULL,
                          product_line varchar(50) NOT NULL,
                          product_scale varchar(10) NOT NULL,
                          product_vendor varchar(50) NOT NULL,
                          product_description text NOT NULL,
                          quantity_in_stock smallint(6) NOT NULL,
                          buy_price decimal(10,2) NOT NULL,
                          msrp decimal(10,2) NOT NULL,
                          PRIMARY KEY (product_code),
                          FOREIGN KEY (product_line) REFERENCES productlines (product_line)
);

CREATE TABLE offices (
                         office_code varchar(10),
                         city varchar(50) NOT NULL,
                         phone varchar(50) NOT NULL,
                         address_line1 varchar(50) NOT NULL,
                         address_line2 varchar(50) DEFAULT NULL,
                         state varchar(50) DEFAULT NULL,
                         country varchar(50) NOT NULL,
                         postal_code varchar(15) NOT NULL,
                         territory varchar(10) NOT NULL,
                         PRIMARY KEY (office_code)
);

CREATE TABLE employees (
                           employee_number int,
                           last_name varchar(50) NOT NULL,
                           first_name varchar(50) NOT NULL,
                           extension varchar(10) NOT NULL,
                           email varchar(100) NOT NULL,
                           office_code varchar(10) NOT NULL,
                           reports_to int DEFAULT NULL,
                           job_title varchar(50) NOT NULL,
                           PRIMARY KEY (employee_number),
                           FOREIGN KEY (reports_to) REFERENCES employees (employee_number),
                           FOREIGN KEY (office_code) REFERENCES offices (office_code)
);

CREATE TABLE customers (
                           customer_number int,
                           customer_name varchar(50) NOT NULL,
                           contact_last_name varchar(50) NOT NULL,
                           contact_first_name varchar(50) NOT NULL,
                           phone varchar(50) NOT NULL,
                           address_line1 varchar(50) NOT NULL,
                           address_line2 varchar(50) DEFAULT NULL,
                           city varchar(50) NOT NULL,
                           state varchar(50) DEFAULT NULL,
                           postal_code varchar(15) DEFAULT NULL,
                           country varchar(50) NOT NULL,
                           sales_rep_employee_number int DEFAULT NULL,
                           credit_limit decimal(10,2) DEFAULT NULL,
                           PRIMARY KEY (customer_number),
                           FOREIGN KEY (sales_rep_employee_number) REFERENCES employees (employee_number)
);

CREATE TABLE payments (
                          customer_number int,
                          check_number varchar(50) NOT NULL,
                          payment_date date NOT NULL,
                          amount decimal(10,2) NOT NULL,
                          PRIMARY KEY (customer_number,check_number),
                          FOREIGN KEY (customer_number) REFERENCES customers (customer_number)
);

CREATE TABLE orders (
                        order_number int,
                        order_date date NOT NULL,
                        required_date date NOT NULL,
                        shipped_date date DEFAULT NULL,
                        status varchar(15) NOT NULL,
                        comments text,
                        customer_number int NOT NULL,
                        PRIMARY KEY (order_number),
                        FOREIGN KEY (customer_number) REFERENCES customers (customer_number)
);

CREATE TABLE orderdetails (
                              order_number int,
                              product_code varchar(15) NOT NULL,
                              quantity_ordered int NOT NULL,
                              price_each decimal(10,2) NOT NULL,
                              order_line_number smallint(6) NOT NULL,
                              PRIMARY KEY (order_number,product_code),
                              FOREIGN KEY (order_number) REFERENCES orders (order_number),
                              FOREIGN KEY (product_code) REFERENCES products (product_code)
);