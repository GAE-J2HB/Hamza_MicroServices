create table if not exists _auto_ecole
(
    auto_ecole_id     bigint       not null
        primary key,
    adresse           varchar(255) null,
    agrement          varchar(255) null,
    date_autorisation date         null,
    date_ouverture    date         null,
    fax               varchar(255) null,
    ice               varchar(255) null,
    id_fiscale        varchar(255) null,
    image_agrement    varchar(255) null,
    imagerc           varchar(255) null,
    logo              varchar(255) null,
    n_patente         varchar(255) null,
    name              varchar(30)  null,
    num_cmpt          varchar(255) null,
    num_cnss          varchar(255) null,
    num_registre_com  varchar(255) null,
    pays              varchar(255) null,
    tel               varchar(10)  null,
    tva               double       not null,
    ville             varchar(255) null
);

create table if not exists _auto_ecole_seq
(
    next_val bigint null
);

create table if not exists _users
(
    user_id    bigint       not null
        primary key,
    email      varchar(255) null,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    password   varchar(255) null,
    role       varchar(255) null
);

create table if not exists _users_seq
(
    next_val bigint null
);

create table if not exists auto_ecole_user
(
    user_id       bigint not null,
    auto_ecole_id bigint not null,
    constraint FK4aj0fwy4046r16duuyhidr6ie
        foreign key (auto_ecole_id) references _auto_ecole (auto_ecole_id),
    constraint FKlu3xkr041memhvlyc6qblnfjd
        foreign key (user_id) references _users (user_id)
);

create table if not exists categorie_depense
(
    id            bigint       not null
        primary key,
    auto_ecole_id bigint       null,
    description   varchar(255) null,
    libelle       varchar(255) null,
    type_depense  int          null,
    constraint FK_categorie_depense_auto_ecole_id
        foreign key (auto_ecole_id) references _auto_ecole (auto_ecole_id)
);

create table if not exists categorie_depense_seq
(
    next_val bigint null
);

create table if not exists categories
(
    nom           varchar(255) not null
        primary key,
    auto_ecole_id bigint       not null,
    description   varchar(255) null,
    moyen         int          null,
    nbr_qst       int          null,
    constraint FK_categories_auto_ecole_id
        foreign key (auto_ecole_id) references _auto_ecole (auto_ecole_id)
);

create table if not exists moniteurs
(
    cin        varchar(255)                           not null
        primary key,
    nom        varchar(255)                           not null,
    prenom     varchar(255)                           not null,
    categories text                                   not null,
    created_at timestamp                              null,
    updated_at timestamp                              null,
    type       varchar(255) collate utf8mb4_unicode_ci null,
    auto_ecole_id bigint       not null,
    constraint FK_moniteurs_auto_ecole_id
        foreign key (auto_ecole_id) references _auto_ecole (auto_ecole_id)
);

create table if not exists categorie_monis
(
    catego_id   varchar(255) not null,
    moniteur_id varchar(255) not null,
    created_at  timestamp    null,
    updated_at  timestamp    null,
    constraint FK_cate
        foreign key (catego_id) references categories (nom)
            on delete cascade,
    constraint FK_monits
        foreign key (moniteur_id) references moniteurs (cin)
            on delete cascade
);

create table if not exists courtheos
(
    dateCour   date         not null,
    heurDebut  time         not null,
    heurFin    time         not null,
    categorie  varchar(255) not null,
    moniteur   varchar(255) not null,
    created_at timestamp    null,
    updated_at timestamp    null,
    id         int auto_increment
        primary key,
    auto_ecole_id bigint       not null,
    constraint FK_courtheos_auto_ecole_id
        foreign key (auto_ecole_id) references _auto_ecole (auto_ecole_id),
    constraint FK_cour_concerne
        foreign key (categorie) references categories (nom)
            on delete cascade,
    constraint FK_cour_concernea
        foreign key (moniteur) references moniteurs (cin)
            on delete cascade
);

create table if not exists token
(
    id         bigint       not null
        primary key,
    expired    bit          not null,
    revoked    bit          not null,
    token      varchar(255) null,
    token_type varchar(255) null,
    user_id    bigint       null,
    constraint FKitpc2cx3eub3b0cvcakffdc3q
        foreign key (user_id) references _users (user_id)
);

create table if not exists token_seq
(
    next_val bigint null
);

create table if not exists vehicules
(
    matricule   varchar(255) not null
        primary key,
    fournisseur varchar(255) not null,
    marque      varchar(255) not null,
    midele      varchar(255) not null,
    categorie   varchar(255) not null,
    created_at  timestamp    null,
    updated_at  timestamp    null,
    auto_ecole_id bigint       not null,
    constraint FK_vehicules_auto_ecole_id
        foreign key (auto_ecole_id) references _auto_ecole (auto_ecole_id)
);

create table if not exists condidats
(
    cin             varchar(255) not null
        primary key,
    nom             varchar(255) not null,
    prenom          varchar(255) not null,
    categorie       varchar(255) not null,
    created_at      timestamp    null,
    updated_at      timestamp    null,
    nomAr           varchar(12)  null,
    prenomAr        varchar(12)  null,
    dateinscription date         null,
    imageProfil     text         null,
    dateNaissance   date         null,
    LieuNaissance   text         null,
    addAr           text         null,
    addFr           text         null,
    email           varchar(40)  null,
    tel             varchar(15)  null,
    imageCin        text         null,
    observation     text         null,
    formation       varchar(12)  null,
    moniTheo        varchar(15)  null,
    moniPra         varchar(15)  null,
    Vehicule        varchar(15)  null,
    profession      varchar(15)  null,
    id              int auto_increment,
    archived        tinyint(1)   null,
    montant         double       null,
    auto_ecole_id   bigint       null,
    constraint cin
        unique (cin),
    constraint id
        unique (id),
    constraint FK_Condid_cat
        foreign key (categorie) references categories (nom)
            on delete cascade,
    constraint FK_Condidat_monipr
        foreign key (moniPra) references moniteurs (cin)
            on delete cascade,
    constraint FK_Condidat_monith
        foreign key (moniTheo) references moniteurs (cin)
            on delete cascade,
    constraint FK_Condidat_vehicule
        foreign key (Vehicule) references vehicules (matricule)
            on delete cascade,
    constraint FK_condidat_auto_ecole
        foreign key (auto_ecole_id) references _auto_ecole (auto_ecole_id)
            on delete cascade
);

create table if not exists Autrespermis
(
    Dateobtention        varchar(15)  null,
    Lieuobtention        varchar(15)  null,
    LieuobtentionEnArabe varchar(15)  null,
    Pcn                  varchar(15)  null,
    AutrespermisID       int auto_increment
        primary key,
    CondidatID           varchar(255) null,
    CategorieID          varchar(255) null,
    id_condidat          int          null,
    created_at           timestamp    null,
    updated_at           timestamp    null,
    constraint FK_contrat_candidat
        foreign key (CondidatID) references condidats (cin)
            on update cascade on delete cascade,
    constraint FK_contrat_catego
        foreign key (CategorieID) references categories (nom)
            on delete cascade,
    constraint fr_Autrespermis_key
        foreign key (id_condidat) references condidats (id)
            on delete cascade
);

create table if not exists Contrat
(
    Date_debut  varchar(15)  null,
    Date_fin    varchar(15)  null,
    numcontrat  varchar(15)  not null
        primary key,
    auto_ecole_id   bigint       null,
    CondidatID  varchar(255) null,
    id_condidat int          null,
    created_at  timestamp    null,
    updated_at  timestamp    null,
    constraint FK_contrat_candid
        foreign key (CondidatID) references condidats (cin)
            on update cascade on delete cascade,
    constraint F_contrat_key
        foreign key (id_condidat) references condidats (id)
            on delete cascade,
    constraint FK_contrat_auto_ecole
        foreign key (auto_ecole_id) references _auto_ecole (auto_ecole_id)
            on delete cascade
);

create table if not exists Paiement
(
    Banque       varchar(15) null,
    Date_paiment date        null,
    Montant      double      null,
    Reste        double      null,
    Type         varchar(15) null,
    id           int auto_increment
        primary key,
    CondidatID   int         not null,
    created_at   timestamp   not null,
    updated_at   timestamp   not null,
    numCheque    varchar(15) null,
    image        varchar(15) null,
    constraint FK_paiment_effectuer
        foreign key (CondidatID) references condidats (id)
            on delete cascade
);

create table if not exists courpras
(
    dateCour   date         not null,
    heurDebut  time         not null,
    heurFin    time         not null,
    categorie  varchar(255) not null,
    moniteur   varchar(255) not null,
    vehicule   varchar(255) not null,
    created_at timestamp    null,
    updated_at timestamp    null,
    auto_ecole_id   bigint       null,
    id         int auto_increment
        primary key,
    constraint FK_courpr
        foreign key (categorie) references categories (nom)
            on delete cascade,
    constraint FK_courpra
        foreign key (moniteur) references moniteurs (cin)
            on delete cascade,
    constraint FK_courpras
        foreign key (vehicule) references vehicules (matricule)
            on delete cascade,
    constraint FK_courpras_auto_ecole
        foreign key (auto_ecole_id) references _auto_ecole (auto_ecole_id)
            on delete cascade
);

create table if not exists courp_condidats
(
    courp_id    int          not null,
    condidat_id varchar(255) not null,
    created_at  timestamp    null,
    updated_at  timestamp    null,
    presence    tinyint(1)   null,
    id          int auto_increment
        primary key,
    constraint FK_condp
        foreign key (condidat_id) references condidats (cin)
            on delete cascade,
    constraint FK_courpcondds
        foreign key (courp_id) references courpras (id)
            on delete cascade
);

create table if not exists court_condidats
(
    court_id    int          not null,
    condidat_id varchar(255) not null,
    created_at  timestamp    null,
    updated_at  timestamp    null,
    presence    tinyint(1)   null,
    id          int auto_increment
        primary key,
    constraint FK_courtcond
        foreign key (court_id) references courtheos (id)
            on delete cascade,
    constraint FK_courtcondidats
        foreign key (condidat_id) references condidats (cin)
            on delete cascade
);
INSERT INTO `condidats` VALUES ('123456789','John','Doe','A','2023-05-28 12:52:09','2023-05-28 12:52:09','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',4,1,0),('152500021','John','Doe','A','2023-05-28 13:26:13','2023-05-29 14:35:34','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',16,1,0),('152500043','John','Doe','A','2023-05-28 13:20:03','2023-05-29 14:35:34','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',14,1,0),('1525954021','John','Doe','A','2023-05-28 13:45:18','2023-05-29 14:34:01','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',17,1,0),('156200043','John','Doe','A','2023-05-28 13:18:32','2023-05-29 14:34:01','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',13,1,0),('156219043','John','Doe','A','2023-05-28 13:12:26','2023-05-28 13:12:26','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',11,1,0),('156456741','John','Doe','A','2023-05-28 12:59:08','2023-05-28 12:59:08','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',7,1,0),('156456789','John','Doe','A','2023-05-28 12:57:29','2023-05-28 12:57:29','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',6,1,0),('156456799','John','Doe','A','2023-05-28 13:01:34','2023-05-28 13:01:34','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',8,1,0),('156459043','John','Doe','A','2023-05-28 13:10:58','2023-05-28 13:10:58','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',10,1,0),('341162321','John','Doe','A','2023-05-28 23:02:28','2023-05-28 23:02:28','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',21,1,0),('342512321','John','Doe','A','2023-05-28 13:58:15','2023-05-28 13:58:15','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',20,1,0),('452512321','John','Doe','A','2023-05-28 13:55:52','2023-05-28 13:55:52','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',19,1,0),('4525954021','John','Doe','A','2023-05-28 13:49:12','2023-05-28 13:49:12','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',18,1,0),('541134034','John','Doe','A','2023-05-28 23:30:40','2023-05-28 23:30:40','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',28,1,NULL),('541160034','John','Doe','A','2023-05-28 23:19:08','2023-05-28 23:19:08','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',27,1,0),('541162121','John','Doe','A','2023-05-28 23:06:41','2023-05-28 23:06:41','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',23,1,0),('541162123','John','Doe','A','2023-05-28 23:09:25','2023-05-28 23:09:25','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',25,1,0),('C6459034','John','Doe','A','2023-05-29 00:10:07','2023-05-29 14:08:50','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',32,1,NULL),('CD256999','SEFRIWI','AHMED','B',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,0),('CD333473','JALIL','JIHANE','B',NULL,'2023-05-27 22:37:59',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,1,0),('CF422034','John','Doe','A','2023-05-29 01:18:05','2023-05-29 01:18:05','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',35,1,NULL),('CF452034','John','Doe','A','2023-05-30 11:45:20','2023-05-30 11:45:20','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',36,1,NULL),('CF459034','John','Doe','A','2023-05-29 01:09:27','2023-05-29 01:09:27','جون','دو','2023-05-27',NULL,'1990-01-01','City','العنوان','Address','john.doe@example.com','1234567890',NULL,'Some observation','Education','34456','CD345678',NULL,'Professional',34,1,NULL);


