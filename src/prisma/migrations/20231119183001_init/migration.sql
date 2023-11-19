-- CreateTable
CREATE TABLE "roles" (
    "role_name" VARCHAR(20) NOT NULL,

    CONSTRAINT "roles_pkey" PRIMARY KEY ("role_name")
);

-- CreateTable
CREATE TABLE "user_role" (
    "user_id" INTEGER NOT NULL,
    "role_name" VARCHAR(20) NOT NULL,

    CONSTRAINT "user_role_pkey" PRIMARY KEY ("user_id","role_name")
);

-- CreateTable
CREATE TABLE "users" (
    "id" SERIAL NOT NULL,
    "mobile" VARCHAR(255) NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "assets_id" INTEGER,
    "bm_data_id" INTEGER,
    "credentials_id" INTEGER,

    CONSTRAINT "users_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "credentials" (
    "id" SERIAL NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    "password_date" DATE NOT NULL,
    "username" VARCHAR(255) NOT NULL,
    "user_id" INTEGER,

    CONSTRAINT "credentials_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "assets" (
    "id" SERIAL NOT NULL,
    "creation_date" TIMESTAMP(6),
    "modification_date" TIMESTAMP(6),
    "creation_person_id" INTEGER,
    "modification_person_id" INTEGER,

    CONSTRAINT "assets_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "ingredients" (
    "id" SERIAL NOT NULL,
    "category" VARCHAR(255),
    "description" VARCHAR(255),
    "type" VARCHAR(255),
    "assets_id" INTEGER,
    "minerals_id" INTEGER,
    "nutrients_id" INTEGER,
    "vitamins_id" INTEGER,

    CONSTRAINT "ingredients_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "bm_data" (
    "id" SERIAL NOT NULL,
    "age" INTEGER,
    "base_bm" DOUBLE PRECISION,
    "diet_type" VARCHAR(255),
    "gender" VARCHAR(255),
    "height" INTEGER,
    "num_days_ex" INTEGER,
    "total_bm" DOUBLE PRECISION,
    "weight" DOUBLE PRECISION,
    "assets_id" INTEGER,

    CONSTRAINT "bm_data_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "nutrients" (
    "id" SERIAL NOT NULL,
    "carbohydrate" DOUBLE PRECISION,
    "cholesterol" DOUBLE PRECISION,
    "fiber" DOUBLE PRECISION,
    "monosaturated_fat" DOUBLE PRECISION,
    "polysaturated_fat" DOUBLE PRECISION,
    "protein" DOUBLE PRECISION,
    "saturated_fat" DOUBLE PRECISION,
    "sugar_total" DOUBLE PRECISION,
    "total_lipid" DOUBLE PRECISION,
    "water" DOUBLE PRECISION,

    CONSTRAINT "nutrients_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "minerals" (
    "id" SERIAL NOT NULL,
    "calcium" DOUBLE PRECISION,
    "copper" DOUBLE PRECISION,
    "iron" DOUBLE PRECISION,
    "magnesium" DOUBLE PRECISION,
    "phosphorus" DOUBLE PRECISION,
    "potassium" DOUBLE PRECISION,
    "sodium" DOUBLE PRECISION,
    "zinc" DOUBLE PRECISION,

    CONSTRAINT "minerals_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "vitamins" (
    "id" SERIAL NOT NULL,
    "niacin" DOUBLE PRECISION,
    "riboflavin" DOUBLE PRECISION,
    "thiamin" DOUBLE PRECISION,
    "vitamin_a" DOUBLE PRECISION,
    "vitamin_b12" DOUBLE PRECISION,
    "vitamin_b6" DOUBLE PRECISION,
    "vitamin_c" DOUBLE PRECISION,
    "vitamin_e" DOUBLE PRECISION,
    "vitamin_k" DOUBLE PRECISION,

    CONSTRAINT "vitamins_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "ingredients_old" (
    "id" SERIAL NOT NULL,
    "alpha_carotene" DOUBLE PRECISION,
    "beta_carotene" DOUBLE PRECISION,
    "beta_cryptoxanthin" DOUBLE PRECISION,
    "calcium" DOUBLE PRECISION,
    "carbohydrate" DOUBLE PRECISION,
    "category" VARCHAR(255),
    "cholesterol" DOUBLE PRECISION,
    "choline" DOUBLE PRECISION,
    "copper" DOUBLE PRECISION,
    "description" VARCHAR(255),
    "fiber" DOUBLE PRECISION,
    "iron" DOUBLE PRECISION,
    "lutein_and_zeaxanthin" DOUBLE PRECISION,
    "lycopene" DOUBLE PRECISION,
    "magnesium" DOUBLE PRECISION,
    "monosaturated_fat" DOUBLE PRECISION,
    "niacin" DOUBLE PRECISION,
    "phosphorus" DOUBLE PRECISION,
    "polysaturated_fat" DOUBLE PRECISION,
    "potassium" DOUBLE PRECISION,
    "protein" DOUBLE PRECISION,
    "retinol" DOUBLE PRECISION,
    "riboflavin" DOUBLE PRECISION,
    "saturated_fat" DOUBLE PRECISION,
    "selenium" DOUBLE PRECISION,
    "sodium" DOUBLE PRECISION,
    "sugar_total" DOUBLE PRECISION,
    "thiamin" DOUBLE PRECISION,
    "total_lipid" DOUBLE PRECISION,
    "vitamin_a" DOUBLE PRECISION,
    "vitamin_b12" DOUBLE PRECISION,
    "vitamin_b6" DOUBLE PRECISION,
    "vitamin_c" DOUBLE PRECISION,
    "vitamin_e" DOUBLE PRECISION,
    "vitamin_k" DOUBLE PRECISION,
    "water" DOUBLE PRECISION,
    "zinc" DOUBLE PRECISION,

    CONSTRAINT "ingredients_old_pkey" PRIMARY KEY ("id")
);

-- CreateIndex
CREATE UNIQUE INDEX "uk_63cf888pmqtt5tipcne79xsbm" ON "users"("mobile");

-- CreateIndex
CREATE UNIQUE INDEX "uk_6pka8top3ggqmjvppakv4ygl8" ON "credentials"("email");

-- CreateIndex
CREATE UNIQUE INDEX "uk_l7xhygibdj6cgkpj2ih1jgx14" ON "credentials"("username");

-- AddForeignKey
ALTER TABLE "user_role" ADD CONSTRAINT "fk_role" FOREIGN KEY ("role_name") REFERENCES "roles"("role_name") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "user_role" ADD CONSTRAINT "fk_user" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "users" ADD CONSTRAINT "fk406jisi7tgpwlu4k6uav4h0bx" FOREIGN KEY ("assets_id") REFERENCES "assets"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "users" ADD CONSTRAINT "fk732k8fvdmf8q8msul077ck3a5" FOREIGN KEY ("credentials_id") REFERENCES "credentials"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "users" ADD CONSTRAINT "fktqen8lvkhn2x84wagd35tfxvs" FOREIGN KEY ("bm_data_id") REFERENCES "bm_data"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "credentials" ADD CONSTRAINT "fkcbcgksvnqvqxrrc4dwv3qys65" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "assets" ADD CONSTRAINT "fklhhvd9iiqu7ud34evcav9xibn" FOREIGN KEY ("modification_person_id") REFERENCES "users"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "assets" ADD CONSTRAINT "fkr3a8lnxg6h2cneapcqvg9yavh" FOREIGN KEY ("creation_person_id") REFERENCES "users"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "ingredients" ADD CONSTRAINT "fk6fe3whlsxbpdqkrmkuvsms7gy" FOREIGN KEY ("assets_id") REFERENCES "assets"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "ingredients" ADD CONSTRAINT "fk8apk6yhxwg8xfu4bao1ka023t" FOREIGN KEY ("vitamins_id") REFERENCES "vitamins"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "ingredients" ADD CONSTRAINT "fk8wk1awxbpt2kuyl80a0mpgunu" FOREIGN KEY ("minerals_id") REFERENCES "minerals"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "ingredients" ADD CONSTRAINT "fkonxm9wwtn0qhjgbu6ms1sr5kg" FOREIGN KEY ("nutrients_id") REFERENCES "nutrients"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "bm_data" ADD CONSTRAINT "fktbluf89juhekonrye78yh6drs" FOREIGN KEY ("assets_id") REFERENCES "assets"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
