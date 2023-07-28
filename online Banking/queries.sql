CREATE TABLE public.transaction_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    online_transfer BOOLEAN
);



CREATE TABLE public.transaction_limit (
    id SERIAL PRIMARY KEY,
    account INTEGER NOT NULL,
    transaction INTEGER NOT NULL,
    transaction_limit NUMERIC(10,2) NOT NULL,
    CONSTRAINT transaction_limit_account_fkey FOREIGN KEY (account) REFERENCES account_type(id) ON DELETE CASCADE,
    CONSTRAINT transaction_limit_transaction_fkey FOREIGN KEY (transaction) REFERENCES transaction_type(id) ON DELETE CASCADE
);



CREATE TABLE public.account_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    minimum_balance NUMERIC(10,2) NOT NULL,
    for_minor BOOLEAN NOT NULL
);



CREATE TABLE public.roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);



CREATE TABLE public.branch (
    id SERIAL PRIMARY KEY,
    city VARCHAR(30) NOT NULL,
    ifsc_code VARCHAR(10) NOT NULL,
    address VARCHAR(50) NOT NULL
);



CREATE TABLE public.beneficiary (
    id SERIAL PRIMARY KEY,
    userid INTEGER NOT NULL,
    nick_name VARCHAR(20) NOT NULL,
    beneficiaryid INTEGER NOT NULL,
    CONSTRAINT beneficiery_userid_fkey FOREIGN KEY (userid) REFERENCES "user"(id) ON DELETE CASCADE,
    CONSTRAINT beneficiery_beneficieryid_fkey FOREIGN KEY (beneficiaryid) REFERENCES account(id) ON DELETE CASCADE
);



CREATE TABLE public.transaction (
    id SERIAL PRIMARY KEY,
    accountid INTEGER,
    amount NUMERIC(17,2) NOT NULL,
    transaction_date DATE NOT NULL,
    transferid INTEGER,
    transaction_type INTEGER NOT NULL,
    branchid INTEGER,
    CONSTRAINT transaction_accountid_fkey FOREIGN KEY (accountid) REFERENCES account(id) ON DELETE CASCADE,
    CONSTRAINT transaction_transferid_fkey FOREIGN KEY (transferid) REFERENCES account(id) ON DELETE CASCADE,
    CONSTRAINT fkey FOREIGN KEY (transaction_type) REFERENCES transaction_type(id) ON DELETE CASCADE,
    CONSTRAINT fkey1 FOREIGN KEY (branchid) REFERENCES branch(id) ON DELETE CASCADE
);



CREATE TABLE public.account (
    id SERIAL PRIMARY KEY,
    userid INTEGER NOT NULL,
    account_number VARCHAR(12) NOT NULL,
    transaction_pin VARCHAR(6),
    balance NUMERIC(17,2) NOT NULL DEFAULT 0,
    account_type INTEGER NOT NULL,
    status CHAR(1) NOT NULL DEFAULT 'i',
    CONSTRAINT account_userid_fkey FOREIGN KEY (userid) REFERENCES "user"(id) ON DELETE CASCADE,
    CONSTRAINT fkey FOREIGN KEY (account_type) REFERENCES account_type(id) ON DELETE CASCADE
);


CREATE TABLE public.user (
    id SERIAL PRIMARY KEY,
    phone_no BIGINT NOT NULL,
    password VARCHAR(15),
    email VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    date_of_birth DATE NOT NULL,
    address VARCHAR(50) NOT NULL,
    branchid INTEGER,
    aadhar_no BIGINT NOT NULL,
    role INTEGER NOT NULL,
    status BOOLEAN NOT NULL DEFAULT true,
    CONSTRAINT fk_key FOREIGN KEY (branchid) REFERENCES branch(id) ON DELETE CASCADE,
    CONSTRAINT fkey FOREIGN KEY (role) REFERENCES roles(id) ON DELETE CASCADE
);

