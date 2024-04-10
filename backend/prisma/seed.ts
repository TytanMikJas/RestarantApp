import { PrismaClient } from '@prisma/client';
import { Category } from '@prisma/client';

const prisma = new PrismaClient();

async function main() {
  await seedTables();
  await seedMenuItems();
}

async function seedTables() {
  await prisma.table.createMany({
    data: [
      {
        id: 1,
      },
      {
        id: 2,
      },
      {
        id: 3,
      },
      {
        id: 4,
      },
      {
        id: 5,
      },
      {
        id: 6,
      },
      {
        id: 7,
      },
      {
        id: 8,
      },
    ],
  });
}

async function seedMenuItems() {
  await prisma.menuItem.createMany({
    data: [
      {
        name: 'Jajecznica z cebulką',
        description:
          'Puszysta jajecznica z chrupiącym boczkiem, podawana z świeżym pieczywem i cebulką.',
        alergens: ['jajka', 'mleko', 'gluten'],
        price: 15.99,
        category: Category.BREAKFAST,
      },
      {
        name: 'Naleśniki z serem',
        description:
          'Delikatne naleśniki nadziewane słodkim serem, podane z sosem waniliowym i owocami.',
        alergens: ['gluten', 'mleko', 'jajka'],
        price: 18.99,
        category: Category.BREAKFAST,
      },
      {
        name: 'Kanapka ze smalcem',
        description:
          'Chrupiąca kanapka z domowym smalcem i ogórkami kiszonymi, posypana szczypiorkiem.',
        alergens: ['gluten'],
        price: 12.99,
        category: Category.BREAKFAST,
      },
      {
        name: 'Żurek na Zakwasie',
        description:
          'Tradycyjna polska zupa żurek z białą kiełbasą, jajkiem i świeżym majerankiem, podawana w chlebie.',
        alergens: ['gluten', 'jajka', 'seler'],
        price: 24.99,
        category: Category.LUNCH,
      },
      {
        name: 'Kapuśniak z Kiełbasą',
        description:
          'Gęsta zupa z kwaszonej kapusty z dodatkiem kiełbasy i ziemniaków, doprawiona majerankiem.',
        alergens: ['seler'],
        price: 19.99,
        category: Category.LUNCH,
      },
      {
        name: 'Barszczyk Czerwony z Uszkami',
        description:
          'Klasyczny barszcz czerwony podawany z uszkami nadziewanymi grzybami.',
        alergens: ['gluten', 'seler'],
        price: 21.99,
        category: Category.LUNCH,
      },
      {
        name: 'Kotlet Schabowy',
        description:
          'Panierowany kotlet schabowy podawany z ziemiaczkami i surówką z białej kapusty.',
        alergens: ['gluten', 'jajka', 'mleko'],
        price: 29.99,
        category: Category.LUNCH,
      },
      {
        name: 'Łazanki z Kapustą i Grzybami',
        description:
          'Tradycyjne łazanki z kiszona kapustą, grzybami leśnymi i kawałkami mięsa, doprawione majerankiem.',
        alergens: ['gluten', 'seler'],
        price: 23.99,
        category: Category.LUNCH,
      },
      {
        name: 'Sernik na Zimno',
        description:
          'Kremowy sernik na zimno, z delikatną warstwą galaretki na wierzchu, na kruchym spodzie z ciasteczek.',
        alergens: ['gluten', 'mleko', 'jajka'],
        price: 14.99,
        category: Category.DESSERT,
      },
      {
        name: 'Szarlotka Jabłkowa',
        description:
          'Lekki, owocowy deser z warstwami świeżych jabłek i delikatnej pianki, posypany cynamonem.',
        alergens: ['gluten', 'jajka', 'mleko'],
        price: 12.99,
        category: Category.DESSERT,
      },
      {
        name: 'Pączki z Różą',
        description:
          'Tradycyjne polskie pączki, nadziewane słodkim dżemem różanym, obsypane cukrem pudrem.',
        alergens: ['gluten', 'jajka', 'mleko'],
        price: 9.99,
        category: Category.DESSERT,
      },
      {
        name: 'Kompot z Suszu',
        description:
          'Tradycyjny, domowy napój przygotowany z suszonych owoców, idealny do obiadu.',
        alergens: [],
        price: 4.99,
        category: Category.DRINKS,
      },
      {
        name: 'Miód Pitny',
        description:
          'Słodki, rozgrzewający napój alkoholowy na bazie miodu, serwowany na ciepło.',
        alergens: [],
        price: 8.99,
        category: Category.DRINKS,
      },
      {
        name: 'Herbata z Przypinką',
        description:
          'Mocna, czarna herbata z dodatkiem polskiego spirytusu, podawana z plasterkiem cytryny.',
        alergens: [],
        price: 7.99,
        category: Category.DRINKS,
      },
      {
        name: 'Piwo Tyskie czteropak',
        description:
          'Jedno z najpopularniejszych polskich piw, charakteryzujące się złocistym kolorem i wyraźną goryczką.',
        alergens: ['gluten'],
        price: 6.99,
        category: Category.DRINKS,
      },
      {
        name: 'Chleb na Zakwasie',
        description:
          'Domowy chleb na zakwasie, o chrupiącej skórce i miękkim, aromatycznym miąższu.',
        alergens: ['gluten'],
        price: 5.99,
        category: Category.ADDITIONALS,
      },
      {
        name: 'Ogórek Kiszony',
        description:
          'Chrupiące, domowe ogórki kiszone, doskonałe jako dodatek do każdego posiłku.',
        alergens: [],
        price: 3.99,
        category: Category.ADDITIONALS,
      },
    ],
  });
}

main()
  .catch((e) => {
    console.error(e);
    process.exit(1);
  })
  .finally(async () => {
    await prisma.$disconnect();
  });
