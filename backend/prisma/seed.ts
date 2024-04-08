const prisma = new PrismaClient();

async function main() {
  await seedTables();
  await seedMenuItems();
}

async function seedTables() {
  return 1;
}

async function seedMenuItems() {
  return 1;
}

main()
  .catch((e) => {
    console.error(e);
    process.exit(1);
  })
  .finally(async () => {
    await prisma.$disconnect();
  });
