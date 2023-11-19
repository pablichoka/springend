-- DropForeignKey
ALTER TABLE "assets" DROP CONSTRAINT "fklhhvd9iiqu7ud34evcav9xibn";

-- DropForeignKey
ALTER TABLE "assets" DROP CONSTRAINT "fkr3a8lnxg6h2cneapcqvg9yavh";

-- DropForeignKey
ALTER TABLE "bm_data" DROP CONSTRAINT "fktbluf89juhekonrye78yh6drs";

-- DropForeignKey
ALTER TABLE "credentials" DROP CONSTRAINT "credentials_assets_id_fkey";

-- DropForeignKey
ALTER TABLE "credentials" DROP CONSTRAINT "fkcbcgksvnqvqxrrc4dwv3qys65";

-- DropForeignKey
ALTER TABLE "ingredients" DROP CONSTRAINT "fk6fe3whlsxbpdqkrmkuvsms7gy";

-- DropForeignKey
ALTER TABLE "ingredients" DROP CONSTRAINT "fk8apk6yhxwg8xfu4bao1ka023t";

-- DropForeignKey
ALTER TABLE "ingredients" DROP CONSTRAINT "fk8wk1awxbpt2kuyl80a0mpgunu";

-- DropForeignKey
ALTER TABLE "ingredients" DROP CONSTRAINT "fkonxm9wwtn0qhjgbu6ms1sr5kg";

-- DropForeignKey
ALTER TABLE "user_role" DROP CONSTRAINT "fk_role";

-- AddForeignKey
ALTER TABLE "user_role" ADD CONSTRAINT "fk_role" FOREIGN KEY ("role_name") REFERENCES "roles"("role_name") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "credentials" ADD CONSTRAINT "credentials_assets_id_fkey" FOREIGN KEY ("assets_id") REFERENCES "assets"("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "credentials" ADD CONSTRAINT "fkcbcgksvnqvqxrrc4dwv3qys65" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "assets" ADD CONSTRAINT "fklhhvd9iiqu7ud34evcav9xibn" FOREIGN KEY ("modification_person_id") REFERENCES "users"("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "assets" ADD CONSTRAINT "fkr3a8lnxg6h2cneapcqvg9yavh" FOREIGN KEY ("creation_person_id") REFERENCES "users"("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "ingredients" ADD CONSTRAINT "fk6fe3whlsxbpdqkrmkuvsms7gy" FOREIGN KEY ("assets_id") REFERENCES "assets"("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "ingredients" ADD CONSTRAINT "fk8apk6yhxwg8xfu4bao1ka023t" FOREIGN KEY ("vitamins_id") REFERENCES "vitamins"("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "ingredients" ADD CONSTRAINT "fk8wk1awxbpt2kuyl80a0mpgunu" FOREIGN KEY ("minerals_id") REFERENCES "minerals"("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "ingredients" ADD CONSTRAINT "fkonxm9wwtn0qhjgbu6ms1sr5kg" FOREIGN KEY ("nutrients_id") REFERENCES "nutrients"("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "bm_data" ADD CONSTRAINT "fktbluf89juhekonrye78yh6drs" FOREIGN KEY ("assets_id") REFERENCES "assets"("id") ON DELETE CASCADE ON UPDATE NO ACTION;
