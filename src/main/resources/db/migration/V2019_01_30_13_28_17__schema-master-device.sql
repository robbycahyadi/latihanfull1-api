create table master_device (
  id                varchar(64) not null primary key,
  name              varchar(150) not null,
  code              varchar(100) not null,
  description       text,
  category_id       varchar(64) not null,
  color_id          varchar(64) not null,
  brand_id          varchar(64) not null,
  condition_id      varchar(64) not null,
  unit_capacity_id  varchar(64) not null,
  loan_status_id    varchar(64) not null
) engine = InnoDB;

alter table master_device
  add constraint fk_device_category foreign key (category_id)
    references device_category (id) on update cascade on delete restrict;

alter table master_device
  add constraint fk_device_color foreign key (color_id)
    references master_color (id) on update cascade on delete restrict;

alter table master_device
  add constraint fk_device_brand foreign key (brand_id)
    references device_brand (id) on update cascade on delete restrict;

alter table master_device
  add constraint fk_device_condition foreign key (condition_id)
    references device_condition (id) on update cascade on delete restrict;

alter table master_device
  add constraint fk_device_unit_capacity foreign key (unit_capacity_id)
    references device_unit_capacity (id) on update cascade on delete restrict;

alter table master_device
  add constraint fk_device_loan_status foreign key (loan_status_id)
    references device_loan_status (id) on update cascade on delete restrict;
