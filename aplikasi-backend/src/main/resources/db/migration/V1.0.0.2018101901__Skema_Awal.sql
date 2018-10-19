create table nasabah (
    id varchar(36),
    no_handphone varchar(100) not null,
    nama varchar(255) not null,
    primary key (id),
    unique(no_handphone)
) Engine=InnoDB ;

create table pembayaran (
    id varchar(36),
    id_nasabah varchar(36) not null,
    waktu_pembayaran datetime not null,
    nilai decimal(19,2) not null,
    fee decimal(19,2) not null,
    referensi varchar(100) not null,
    primary key (id),
    foreign key (id_nasabah) references nasabah (id)
) Engine=InnoDB ;
