-- CreateTable
CREATE TABLE "Roles" (
    "id" TEXT NOT NULL,

    CONSTRAINT "Roles_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Users" (
    "id" INTEGER NOT NULL,
    "assets_id" INTEGER NOT NULL,
    "bm_data_id" INTEGER NOT NULL,
    "username" TEXT NOT NULL,
    "email" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "mobile" TEXT NOT NULL,
    "password" TEXT NOT NULL,
    "password_date" DATE NOT NULL,

    CONSTRAINT "Users_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Assets" (
    "id" INTEGER NOT NULL,
    "creation_date" DATE NOT NULL,
    "creation_person" INTEGER NOT NULL,
    "modification_date" DATE NOT NULL,
    "modification_person" INTEGER NOT NULL,

    CONSTRAINT "Assets_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "BM_Data" (
    "id" INTEGER NOT NULL,
    "user_id" TEXT,
    "age" INTEGER,
    "height" INTEGER,
    "num_days_ex" INTEGER,
    "diet_type" TEXT,
    "gender" TEXT,
    "weight" DOUBLE PRECISION,
    "total_bm" DOUBLE PRECISION,
    "base_bm" DOUBLE PRECISION,

    CONSTRAINT "BM_Data_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Ingredients" (
    "id" INTEGER NOT NULL,
    "nutrients_id" INTEGER NOT NULL,
    "minerals_id" INTEGER NOT NULL,
    "vitamins_id" INTEGER NOT NULL,
    "assets_id" INTEGER NOT NULL,
    "name" TEXT NOT NULL,
    "category" TEXT NOT NULL,
    "description" TEXT NOT NULL,

    CONSTRAINT "Ingredients_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Nutrients" (
    "id" INTEGER NOT NULL,
    "ingredients_id" INTEGER NOT NULL,
    "carbohydrate" DOUBLE PRECISION NOT NULL,
    "cholesterol" DOUBLE PRECISION NOT NULL,
    "monosaturated_fat" DOUBLE PRECISION NOT NULL,
    "polysaturated_fat" DOUBLE PRECISION NOT NULL,
    "saturated_fat" DOUBLE PRECISION NOT NULL,
    "total_lipid" DOUBLE PRECISION NOT NULL,
    "fiber" DOUBLE PRECISION NOT NULL,
    "protein" DOUBLE PRECISION NOT NULL,
    "sugar_total" DOUBLE PRECISION NOT NULL,
    "water" DOUBLE PRECISION NOT NULL,

    CONSTRAINT "Nutrients_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Minerals" (
    "id" INTEGER NOT NULL,
    "ingredients_id" INTEGER NOT NULL,
    "calcium" DOUBLE PRECISION NOT NULL,
    "copper" DOUBLE PRECISION NOT NULL,
    "iron" DOUBLE PRECISION NOT NULL,
    "magnesium" DOUBLE PRECISION NOT NULL,
    "phosphorus" DOUBLE PRECISION NOT NULL,
    "potassium" DOUBLE PRECISION NOT NULL,
    "sodium" DOUBLE PRECISION NOT NULL,
    "zinc" DOUBLE PRECISION NOT NULL,

    CONSTRAINT "Minerals_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Vitamins" (
    "id" INTEGER NOT NULL,
    "ingredients_id" INTEGER NOT NULL,
    "vitamin_a" DOUBLE PRECISION NOT NULL,
    "vitamin_b12" DOUBLE PRECISION NOT NULL,
    "vitamin_b6" DOUBLE PRECISION NOT NULL,
    "vitamin_c" DOUBLE PRECISION NOT NULL,
    "vitamin_e" DOUBLE PRECISION NOT NULL,
    "vitamin_k" DOUBLE PRECISION NOT NULL,
    "niacin" DOUBLE PRECISION NOT NULL,
    "riboflavin" DOUBLE PRECISION NOT NULL,
    "thiamin" DOUBLE PRECISION NOT NULL,

    CONSTRAINT "Vitamins_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "IngredientsOld" (
    "id" INTEGER NOT NULL,
    "name" TEXT NOT NULL,
    "category" TEXT NOT NULL,
    "description" TEXT NOT NULL,
    "alpha_carotene" DOUBLE PRECISION NOT NULL,
    "beta_carotene" DOUBLE PRECISION NOT NULL,
    "beta_cryptoxanthin" DOUBLE PRECISION NOT NULL,
    "carbohydrate" DOUBLE PRECISION NOT NULL,
    "cholesterol" DOUBLE PRECISION NOT NULL,
    "choline" DOUBLE PRECISION NOT NULL,
    "monosaturated_fat" DOUBLE PRECISION NOT NULL,
    "polysaturated_fat" DOUBLE PRECISION NOT NULL,
    "saturated_fat" DOUBLE PRECISION NOT NULL,
    "total_lipid" DOUBLE PRECISION NOT NULL,
    "fiber" DOUBLE PRECISION NOT NULL,
    "lutein_and_zeaxanthin" DOUBLE PRECISION NOT NULL,
    "lycopene" DOUBLE PRECISION NOT NULL,
    "calcium" DOUBLE PRECISION NOT NULL,
    "copper" DOUBLE PRECISION NOT NULL,
    "iron" DOUBLE PRECISION NOT NULL,
    "magnesium" DOUBLE PRECISION NOT NULL,
    "phosphorus" DOUBLE PRECISION NOT NULL,
    "potassium" DOUBLE PRECISION NOT NULL,
    "sodium" DOUBLE PRECISION NOT NULL,
    "zinc" DOUBLE PRECISION NOT NULL,
    "niacin" DOUBLE PRECISION NOT NULL,
    "protein" DOUBLE PRECISION NOT NULL,
    "retinol" DOUBLE PRECISION NOT NULL,
    "riboflavin" DOUBLE PRECISION NOT NULL,
    "selenium" DOUBLE PRECISION NOT NULL,
    "sugar_total" DOUBLE PRECISION NOT NULL,
    "thiamin" DOUBLE PRECISION NOT NULL,
    "vitamin_a" DOUBLE PRECISION NOT NULL,
    "vitamin_b12" DOUBLE PRECISION NOT NULL,
    "vitamin_b6" DOUBLE PRECISION NOT NULL,
    "vitamin_c" DOUBLE PRECISION NOT NULL,
    "vitamin_e" DOUBLE PRECISION NOT NULL,
    "vitamin_k" DOUBLE PRECISION NOT NULL,
    "water" DOUBLE PRECISION NOT NULL,

    CONSTRAINT "IngredientsOld_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "user_roles" (
    "user_id" INTEGER NOT NULL,
    "role_id" TEXT NOT NULL,

    CONSTRAINT "user_roles_pkey" PRIMARY KEY ("user_id","role_id")
);

-- CreateIndex
CREATE UNIQUE INDEX "Users_bm_data_id_key" ON "Users"("bm_data_id");

-- CreateIndex
CREATE UNIQUE INDEX "Users_username_key" ON "Users"("username");

-- CreateIndex
CREATE UNIQUE INDEX "Users_email_key" ON "Users"("email");

-- CreateIndex
CREATE UNIQUE INDEX "Users_mobile_key" ON "Users"("mobile");

-- CreateIndex
CREATE UNIQUE INDEX "Ingredients_nutrients_id_key" ON "Ingredients"("nutrients_id");

-- CreateIndex
CREATE UNIQUE INDEX "Ingredients_minerals_id_key" ON "Ingredients"("minerals_id");

-- CreateIndex
CREATE UNIQUE INDEX "Ingredients_vitamins_id_key" ON "Ingredients"("vitamins_id");

-- AddForeignKey
ALTER TABLE "Users" ADD CONSTRAINT "Users_assets_id_fkey" FOREIGN KEY ("assets_id") REFERENCES "Assets"("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Users" ADD CONSTRAINT "Users_bm_data_id_fkey" FOREIGN KEY ("bm_data_id") REFERENCES "BM_Data"("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Assets" ADD CONSTRAINT "fk_assets_creationperson" FOREIGN KEY ("creation_person") REFERENCES "Users"("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "Assets" ADD CONSTRAINT "fk_assets_modificationperson" FOREIGN KEY ("modification_person") REFERENCES "Users"("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "Ingredients" ADD CONSTRAINT "Ingredients_assets_id_fkey" FOREIGN KEY ("assets_id") REFERENCES "Assets"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Ingredients" ADD CONSTRAINT "Ingredients_minerals_id_fkey" FOREIGN KEY ("minerals_id") REFERENCES "Minerals"("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Ingredients" ADD CONSTRAINT "Ingredients_nutrients_id_fkey" FOREIGN KEY ("nutrients_id") REFERENCES "Nutrients"("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Ingredients" ADD CONSTRAINT "Ingredients_vitamins_id_fkey" FOREIGN KEY ("vitamins_id") REFERENCES "Vitamins"("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "user_roles" ADD CONSTRAINT "fk_role" FOREIGN KEY ("role_id") REFERENCES "Roles"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "user_roles" ADD CONSTRAINT "fk_user" FOREIGN KEY ("user_id") REFERENCES "Users"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

