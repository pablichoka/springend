/*
  Warnings:

  - Made the column `creation_date` on table `assets` required. This step will fail if there are existing NULL values in that column.
  - Made the column `modification_date` on table `assets` required. This step will fail if there are existing NULL values in that column.
  - Made the column `creation_person_id` on table `assets` required. This step will fail if there are existing NULL values in that column.
  - Made the column `modification_person_id` on table `assets` required. This step will fail if there are existing NULL values in that column.

*/
-- AlterTable
ALTER TABLE "assets" ALTER COLUMN "creation_date" SET NOT NULL,
ALTER COLUMN "modification_date" SET NOT NULL,
ALTER COLUMN "creation_person_id" SET NOT NULL,
ALTER COLUMN "modification_person_id" SET NOT NULL;

-- AlterTable
ALTER TABLE "credentials" ALTER COLUMN "password_date" SET DATA TYPE TIMESTAMP(6);
