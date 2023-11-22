/*
  Warnings:

  - Made the column `creation_person_id` on table `assets` required. This step will fail if there are existing NULL values in that column.
  - Made the column `modification_person_id` on table `assets` required. This step will fail if there are existing NULL values in that column.

*/
-- DropForeignKey
ALTER TABLE "assets" DROP CONSTRAINT "fklhhvd9iiqu7ud34evcav9xibn";

-- DropForeignKey
ALTER TABLE "assets" DROP CONSTRAINT "fkr3a8lnxg6h2cneapcqvg9yavh";

-- AlterTable
ALTER TABLE "assets" ALTER COLUMN "creation_person_id" SET NOT NULL,
ALTER COLUMN "modification_person_id" SET NOT NULL;
