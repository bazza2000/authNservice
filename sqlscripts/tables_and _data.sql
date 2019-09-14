create table user_profile (
       user_id varchar(255) not null,
        email varchar(255),
        surname varchar(255),
        first_name varchar(255),
        username varchar(255)
        );

INSERT INTO user_profile VALUES ( 'big-guid-numero-uno', 'jim.lack@viosystems.com', 'Lack', 'Jim', 'jimbo');
INSERT INTO user_profile VALUES ( 'big-guid-numero-duo', 'ashley.taylor@viosystems.com', 'Taylor', 'Ashley', 'ash');
INSERT INTO user_profile VALUES ( 'big-guid-numero-three', 'dermot@viosystems.com', 'Darmuid', 'ODwyer', 'derm');

commit;
