drop table if exists resident;
drop table if exists birth_death_report_resident;
drop table if exists family_relationship;
drop table if exists household;
drop table if exists household_movement_address;
drop table if exists household_composition_resident;
drop table if exists certificate_issue;

create table if not exists resident
(
    resident_serial_number       int          not null
        primary key,
    name                         varchar(100) not null,
    resident_registration_number varchar(300) not null,
    gender_code                  varchar(20)  not null,
    birth_date                   datetime     not null,
    birth_place_code             varchar(20)  not null,
    registration_base_address    varchar(500) not null,
    death_date                   datetime     null,
    death_place_code             varchar(20)  null,
    death_place_address          varchar(500) null
);

create table if not exists birth_death_report_resident
(
    resident_serial_number           int         not null,
    birth_death_type_code            varchar(20) not null,
    report_resident_serial_number    int         not null,
    birth_death_report_date          date        not null,
    birth_report_qualifications_code varchar(20) null,
    death_report_qualifications_code varchar(20) null,
    email_address                    varchar(50) null,
    phone_number                     varchar(20) not null,
    primary key (resident_serial_number, birth_death_type_code),
    constraint resident_birth_death_report_resident_fk
        foreign key (resident_serial_number) references resident (resident_serial_number)
);

create table if not exists certificate_issue
(
    certificate_confirmation_number bigint      not null
        primary key,
    resident_serial_number          int         not null,
    certificate_type_code           varchar(20) not null,
    certificate_issue_date          date        not null,
    constraint resident_certificate_issue_fk
        foreign key (resident_serial_number) references resident (resident_serial_number)
);

create table if not exists family_relationship
(
    base_resident_serial_number   int         not null,
    family_resident_serial_number int         not null,
    family_relationship_code      varchar(20) not null,
    primary key (base_resident_serial_number, family_resident_serial_number),
    constraint resident_family_relationship_fk
        foreign key (family_resident_serial_number) references resident (resident_serial_number),
    constraint resident_family_relationship_fk2
        foreign key (base_resident_serial_number) references resident (resident_serial_number)
);

create table if not exists household
(
    household_serial_number           int          not null
        primary key,
    household_resident_serial_number  int          not null,
    household_composition_date        date         not null,
    household_composition_reason_code varchar(20)  not null,
    current_house_movement_address    varchar(500) not null,
    constraint resident_household_fk
        foreign key (household_resident_serial_number) references resident (resident_serial_number)
);

create table if not exists household_composition_resident
(
    household_serial_number                  int         not null,
    resident_serial_number                   int         not null,
    report_date                              date        not null,
    household_relationship_code              varchar(20) not null,
    household_composition_change_reason_code varchar(20) not null,
    primary key (household_serial_number, resident_serial_number),
    constraint household_household_composition_resident_fk
        foreign key (household_serial_number) references household (household_serial_number),
    constraint resident_household_composition_resident_fk
        foreign key (resident_serial_number) references resident (resident_serial_number)
);

create table if not exists household_movement_address
(
    household_serial_number    int                    not null,
    house_movement_report_date date                   not null,
    house_movement_address     varchar(500)           not null,
    last_address_yn            varchar(1) default 'Y' not null,
    primary key (household_serial_number, house_movement_report_date),
    constraint household_household_movement_address_fk
        foreign key (household_serial_number) references household (household_serial_number)
);

