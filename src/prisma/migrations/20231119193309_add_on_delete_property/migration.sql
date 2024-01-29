-- DropForeignKey
ALTER TABLE "user_role" DROP CONSTRAINT "fk_role";

-- DropForeignKey
ALTER TABLE "user_role" DROP CONSTRAINT "fk_user";

-- AddForeignKey
ALTER TABLE "user_role" ADD CONSTRAINT "fk_role" FOREIGN KEY ("role_name") REFERENCES "roles"("role_name") ON DELETE CASCADE ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "user_role" ADD CONSTRAINT "fk_user" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE CASCADE ON UPDATE NO ACTION;
