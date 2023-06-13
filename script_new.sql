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
    auto_ecole_id bigint not null,
    user_id       bigint not null,
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
    type_depense  varchar(255) not null,
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
    moyen         int          not null,
    created_at    timestamp    null,
    updated_at    timestamp    null,
    auto_ecole_id bigint       null,
    description   varchar(255) null,
    nbr_qst       int          null,
    constraint FK_categories_auto_ecole_id
        foreign key (auto_ecole_id) references _auto_ecole (auto_ecole_id)
);

create table if not exists exam_seq
(
    next_val bigint null
);

create table if not exists moniteurs
(
    cin           varchar(255) not null
        primary key,
    nom           varchar(255) not null,
    prenom        varchar(255) not null,
    categories    text         not null,
    created_at    timestamp    null,
    updated_at    timestamp    null,
    type          varchar(15)  null,
    id_auto_ecole bigint       not null,
    constraint FK_moniteurs_auto_ecole_id
        foreign key (id_auto_ecole) references _auto_ecole (auto_ecole_id)
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
    dateCour      date         not null,
    heurDebut     time         not null,
    heurFin       time         not null,
    categorie     varchar(255) not null,
    moniteur      varchar(255) not null,
    created_at    timestamp    null,
    updated_at    timestamp    null,
    id            int auto_increment
        primary key,
    id_auto_ecole bigint       not null,
    constraint FK_cour_concerne
        foreign key (categorie) references categories (nom)
            on delete cascade,
    constraint FK_cour_concernea
        foreign key (moniteur) references moniteurs (cin)
            on delete cascade,
    constraint FK_courtheos_auto_ecole_id
        foreign key (id_auto_ecole) references _auto_ecole (auto_ecole_id)
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
    matricule     varchar(255) not null
        primary key,
    fournisseur   varchar(255) not null,
    marque        varchar(255) not null,
    midele        varchar(255) not null,
    categorie     varchar(255) not null,
    created_at    timestamp    null,
    updated_at    timestamp    null,
    id_auto_ecole bigint       not null,
    constraint FK_vehicules_auto_ecole_id
        foreign key (id_auto_ecole) references _auto_ecole (auto_ecole_id)
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
    id              bigint auto_increment,
    archived        tinyint(1)   null,
    montant         double       null,
    id_auto_ecole   bigint       not null,
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
    constraint FK_condidat_auto_ecole_id
        foreign key (id_auto_ecole) references _auto_ecole (auto_ecole_id)
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
    id_condidat          bigint       null,
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
    numcontrat  varchar(15)  not null,
    CondidatID  varchar(255) null,
    id_condidat bigint       null,
    created_at  timestamp    null,
    updated_at  timestamp    null,
    constraint FK_contrat_candid
        foreign key (CondidatID) references condidats (cin)
            on update cascade on delete cascade,
    constraint F_contrat_key
        foreign key (id_condidat) references condidats (id)
            on delete cascade
);

create table if not exists Paiement
(
    Banque       varchar(15)  null,
    Date_paiment date         null,
    Montant      double       null,
    Reste        double       null,
    Type         varchar(15)  null,
    id           int auto_increment
        primary key,
    CondidatID   bigint       not null,
    created_at   timestamp    not null,
    updated_at   timestamp    not null,
    numCheque    varchar(15)  null,
    image        varchar(200) null,
    constraint FK_paiment_effectuer
        foreign key (CondidatID) references condidats (id)
            on delete cascade
);

create table if not exists courpras
(
    dateCour      date         not null,
    heurDebut     time         not null,
    heurFin       time         not null,
    categorie     varchar(255) not null,
    moniteur      varchar(255) not null,
    vehicule      varchar(255) not null,
    created_at    timestamp    null,
    updated_at    timestamp    null,
    id            int auto_increment
        primary key,
    id_auto_ecole bigint       not null,
    constraint FK_courpr
        foreign key (categorie) references categories (nom)
            on delete cascade,
    constraint FK_courpr_auto_ecole_id
        foreign key (id_auto_ecole) references _auto_ecole (auto_ecole_id),
    constraint FK_courpra
        foreign key (moniteur) references moniteurs (cin)
            on delete cascade,
    constraint FK_courpras
        foreign key (vehicule) references vehicules (matricule)
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

create table if not exists exam
(
    id            bigint       not null
        primary key,
    categorie     varchar(255) null,
    date_depot    date         null,
    date_examen   date         null,
    id_auto_ecole bigint       not null,
    id_candidate  bigint       not null,
    note          int          not null,
    resultat      bit          not null,
    type          varchar(255) null,
    constraint FK_exam_auto_ecole_id
        foreign key (id_auto_ecole) references _auto_ecole (auto_ecole_id),
    constraint FK_exam_candidate_id
        foreign key (id_candidate) references condidats (id)
);


