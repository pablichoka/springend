-- DropForeignKey
ALTER TABLE "users" DROP CONSTRAINT "fk406jisi7tgpwlu4k6uav4h0bx";

-- DropForeignKey
ALTER TABLE "users" DROP CONSTRAINT "fk732k8fvdmf8q8msul077ck3a5";

-- DropForeignKey
ALTER TABLE "users" DROP CONSTRAINT "fktqen8lvkhn2x84wagd35tfxvs";

-- AlterTable
ALTER TABLE "credentials" ADD COLUMN     "assets_id" INTEGER;

-- AddForeignKey
ALTER TABLE "users" ADD CONSTRAINT "fk406jisi7tgpwlu4k6uav4h0bx" FOREIGN KEY ("assets_id") REFERENCES "assets"("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "users" ADD CONSTRAINT "fk732k8fvdmf8q8msul077ck3a5" FOREIGN KEY ("credentials_id") REFERENCES "credentials"("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "users" ADD CONSTRAINT "fktqen8lvkhn2x84wagd35tfxvs" FOREIGN KEY ("bm_data_id") REFERENCES "bm_data"("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "credentials" ADD CONSTRAINT "credentials_assets_id_fkey" FOREIGN KEY ("assets_id") REFERENCES "assets"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
