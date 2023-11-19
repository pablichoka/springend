/*
  Warnings:

  - You are about to drop the column `user_id` on the `credentials` table. All the data in the column will be lost.

*/
-- DropForeignKey
ALTER TABLE "credentials" DROP CONSTRAINT "fkcbcgksvnqvqxrrc4dwv3qys65";

-- AlterTable
ALTER TABLE "credentials" DROP COLUMN "user_id";
