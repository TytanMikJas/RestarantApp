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
        id: 1,
        name: 'Jajecznica z cebulką',
        description:
          'Puszysta jajecznica z chrupiącym boczkiem, podawana z świeżym pieczywem i cebulką.',
        alergens: ['jajka', 'mleko', 'gluten'],
        price: 15.99,
        category: Category.BREAKFAST,
      },
      {
        id: 2,
        name: 'Naleśniki z serem',
        description:
          'Delikatne naleśniki nadziewane słodkim serem, podane z sosem waniliowym i owocami.',
        alergens: ['gluten', 'mleko', 'jajka'],
        price: 18.99,
        category: Category.BREAKFAST,
      },
      {
        id: 3,
        name: 'Kanapka ze smalcem',
        description:
          'Chrupiąca kanapka z domowym smalcem i ogórkami kiszonymi, posypana szczypiorkiem.',
        alergens: ['gluten'],
        price: 12.99,
        category: Category.BREAKFAST,
      },
      {
        id: 4,
        name: 'Żurek na Zakwasie',
        description:
          'Tradycyjna polska zupa żurek z białą kiełbasą, jajkiem i świeżym majerankiem, podawana w chlebie.',
        alergens: ['gluten', 'jajka', 'seler'],
        price: 24.99,
        category: Category.LUNCH,
      },
      {
        id: 5,
        name: 'Kapuśniak z Kiełbasą',
        description:
          'Gęsta zupa z kwaszonej kapusty z dodatkiem kiełbasy i ziemniaków, doprawiona majerankiem.',
        alergens: ['seler'],
        price: 19.99,
        category: Category.LUNCH,
      },
      {
        id: 6,
        name: 'Barszczyk Czerwony z Uszkami',
        description:
          'Klasyczny barszcz czerwony podawany z uszkami nadziewanymi grzybami.',
        alergens: ['gluten', 'seler'],
        price: 21.99,
        category: Category.LUNCH,
      },
      {
        id: 7,
        name: 'Kotlet Schabowy',
        description:
          'Panierowany kotlet schabowy podawany z ziemiaczkami i surówką z białej kapusty.',
        alergens: ['gluten', 'jajka', 'mleko'],
        price: 29.99,
        category: Category.LUNCH,
      },
      {
        id: 8,
        name: 'Łazanki z Kapustą i Grzybami',
        description:
          'Tradycyjne łazanki z kiszona kapustą, grzybami leśnymi i kawałkami mięsa, doprawione majerankiem.',
        alergens: ['gluten', 'seler'],
        price: 23.99,
        category: Category.LUNCH,
      },
      {
        id: 9,
        name: 'Sernik na Zimno',
        description:
          'Kremowy sernik na zimno, z delikatną warstwą galaretki na wierzchu, na kruchym spodzie z ciasteczek.',
        alergens: ['gluten', 'mleko', 'jajka'],
        price: 14.99,
        category: Category.DESSERT,
      },
      {
        id: 10,
        name: 'Szarlotka Jabłkowa',
        description:
          'Lekki, owocowy deser z warstwami świeżych jabłek i delikatnej pianki, posypany cynamonem.',
        alergens: ['gluten', 'jajka', 'mleko'],
        price: 12.99,
        category: Category.DESSERT,
      },
      {
        id: 11,
        name: 'Pączki z Różą',
        description:
          'Tradycyjne polskie pączki, nadziewane słodkim dżemem różanym, obsypane cukrem pudrem.',
        alergens: ['gluten', 'jajka', 'mleko'],
        price: 9.99,
        category: Category.DESSERT,
      },
      {
        id: 12,
        name: 'Kompot z Suszu',
        description:
          'Tradycyjny, domowy napój przygotowany z suszonych owoców, idealny do obiadu.',
        alergens: [],
        price: 4.99,
        category: Category.DRINKS,
      },
      {
        id: 13,
        name: 'Miód Pitny',
        description:
          'Słodki, rozgrzewający napój alkoholowy na bazie miodu, serwowany na ciepło.',
        alergens: [],
        price: 8.99,
        category: Category.DRINKS,
      },
      {
        id: 14,
        name: 'Herbata z Przypinką',
        description:
          'Mocna, czarna herbata z dodatkiem polskiego spirytusu, podawana z plasterkiem cytryny.',
        alergens: [],
        price: 7.99,
        category: Category.DRINKS,
      },
      {
        id: 15,
        name: 'Piwo Tyskie czteropak',
        description:
          'Jedno z najpopularniejszych polskich piw, charakteryzujące się złocistym kolorem i wyraźną goryczką.',
        alergens: ['gluten'],
        price: 6.99,
        category: Category.DRINKS,
      },
      {
        id: 16,
        name: 'Chleb na Zakwasie',
        description:
          'Domowy chleb na zakwasie, o chrupiącej skórce i miękkim, aromatycznym miąższu.',
        alergens: ['gluten'],
        price: 5.99,
        category: Category.ADDITIONALS,
      },
      {
        id: 17,
        name: 'Ogórek Kiszony',
        description:
          'Chrupiące, domowe ogórki kiszone, doskonałe jako dodatek do każdego posiłku.',
        alergens: [],
        price: 3.99,
        category: Category.ADDITIONALS,
      },
    ],
  });

  //attachements for Ogórek kiszony
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'ogorek-kiszony/ogorek1.jpg',
        menuItemId: 17,
        fileType: 'IMAGE',
      },
      {
        fileName: 'ogorek-kiszony/ogorek2.jpg',
        menuItemId: 17,
        fileType: 'IMAGE',
      },
    ],
  });

  //attachements for Chleb na Zakwasie
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'chleb-na-zakwasie/chleb1.jpg',
        menuItemId: 16,
        fileType: 'IMAGE',
      },
      {
        fileName: 'chleb-na-zakwasie/chleb2.jpg',
        menuItemId: 16,
        fileType: 'IMAGE',
      },
    ],
  });

  //attachements for Piwo Tyskie czteropak
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'piwo-tyskie-czteropak/kufel1.jpg',
        menuItemId: 15,
        fileType: 'IMAGE',
      },
      {
        fileName: 'piwo-tyskie/kufel2.jpg',
        menuItemId: 15,
        fileType: 'IMAGE',
      },
      {
        fileName: 'piwo-tyskie/piwo.jpg',
        menuItemId: 15,
        fileType: 'IMAGE',
      },
    ],
  });

  //attachements for Herbata z Przypinką
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'herbata-z-przypinka/herbata1.jpg',
        menuItemId: 14,
        fileType: 'IMAGE',
      },
      {
        fileName: 'herbata-z-przypinka/herbaty.jpg',
        menuItemId: 14,
        fileType: 'IMAGE',
      },
    ],
  });

  //attachements for Miód Pitny
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'miod-pitny/miod1.jpg',
        menuItemId: 13,
        fileType: 'IMAGE',
      },
      {
        fileName: 'miod-pitny/miod2.jpg',
        menuItemId: 13,
        fileType: 'IMAGE',
      },
    ],
  });

  //attachements for Kompot z Suszu
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'kompot-z-suszu/garnek.jpg',
        menuItemId: 12,
        fileType: 'IMAGE',
      },
      {
        fileName: 'kompot-z-suszu/kompot.jpg',
        menuItemId: 12,
        fileType: 'IMAGE',
      },
      {
        fileName: 'kompot-z-suszu/przyprawy.jpg',
        menuItemId: 12,
        fileType: 'IMAGE',
      },
    ],
  });

  //attachements for Pączki z Różą
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'paczki-z-roza/nadziewanie.jpg',
        menuItemId: 11,
        fileType: 'IMAGE',
      },
      {
        fileName: 'paczki-z-roza/paczek.jpg',
        menuItemId: 11,
        fileType: 'IMAGE',
      },
    ],
  });

  //attachements for Szarlotka Jabłkowa
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'szarlotka-jablkowa/lody.jpg',
        menuItemId: 10,
        fileType: 'IMAGE',
      },
      {
        fileName: 'szarlotka-jablkowa/szarlotka.jpg',
        menuItemId: 10,
        fileType: 'IMAGE',
      },
      {
        fileName: 'szarlotka-jablkowa/szarlotkaVod.mp4',
        menuItemId: 10,
        fileType: 'VIDEO',
      },
    ],
  });

  //attachements for Sernik na Zimno
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'sernik-na-zimno/miska.jpg',
        menuItemId: 9,
        fileType: 'IMAGE',
      },
      {
        fileName: 'sernik-na-zimno/sernik.jpg',
        menuItemId: 9,
        fileType: 'IMAGE',
      },
    ],
  });

  //copilot, please take filenames from /public directory, ok?
  //here is copilots anwser: sure, I will do it for you

  //attachements for Łazanki z Kapustą i Grzybami
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'lazanki-z-kapusta-i-grzybami/grzyby.jpg',
        menuItemId: 8,
        fileType: 'IMAGE',
      },
      {
        fileName: 'lazanki-z-kapusta-i-grzybami/lazanki.jpg',
        menuItemId: 8,
        fileType: 'IMAGE',
      },
    ],
  });

  //attachements for Kotlet Schabowy
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'kotlet-schabowy/kapusta.jpg',
        menuItemId: 7,
        fileType: 'IMAGE',
      },
      {
        fileName: 'kotlet-schabowy/kotlet.jpg',
        menuItemId: 7,
        fileType: 'IMAGE',
      },
      {
        fileName: 'kotlet-schabowy/schaboweVod.mp4',
        menuItemId: 7,
        fileType: 'VIDEO',
      },
      {
        fileName: 'kotlet-schabowy/puree.jpg',
        menuItemId: 7,
        fileType: 'IMAGE',
      },
    ],
  });

  //attachements for Barszczyk Czerwony z Uszkami
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'barszczyk-czerwony-z-uszkami/barszcz1.jpg',
        menuItemId: 6,
        fileType: 'IMAGE',
      },
      {
        fileName: 'barszczyk-czerwony-z-uszkami/barszcz2.jpg',
        menuItemId: 6,
        fileType: 'IMAGE',
      },
      {
        fileName: 'barszczyk-czerwony-z-uszkami/uszka.jpg',
        menuItemId: 6,
        fileType: 'IMAGE',
      },
      {
        fileName: 'barszczyk-czerwony-z-uszkami/barszczVod.mp4',
        menuItemId: 6,
        fileType: 'VIDEO',
      },
    ],
  });

  //attachements for Kapuśniak z Kiełbasą
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'kapusniak-z-kielbasa/kapusta.jpg',
        menuItemId: 5,
        fileType: 'IMAGE',
      },
      {
        fileName: 'kapusniak-z-kielbasa/kielbasa.jpg',
        menuItemId: 5,
        fileType: 'IMAGE',
      },
      {
        fileName: 'kapusniak-z-kielbasa/kapusniak.jpg',
        menuItemId: 5,
        fileType: 'IMAGE',
      },
      {
        fileName: 'kapusniak-z-kielbasa/kapusniakVod.mp4',
        menuItemId: 5,
        fileType: 'VIDEO',
      },
    ],
  });

  //attachements for Żurek na Zakwasie
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'zurek-na-zakwasie/zurek1.jpg',
        menuItemId: 4,
        fileType: 'IMAGE',
      },
      {
        fileName: 'zurek-na-zakwasie/zurek1.jpg',
        menuItemId: 4,
        fileType: 'IMAGE',
      },
    ],
  });

  //attachements for Kanapka ze smalcem
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'kanapka-ze-smalcem/kanapka.jpg',
        menuItemId: 3,
        fileType: 'IMAGE',
      },
      {
        fileName: 'kanapka-ze-smalcem/ogorek.jpg',
        menuItemId: 3,
        fileType: 'IMAGE',
      },
      {
        fileName: 'kanapka-ze-smalcem/smalec.jpg',
        menuItemId: 3,
        fileType: 'IMAGE',
      },
      {
        fileName: 'kanapka-ze-smalcem/smalecVod.mp4',
        menuItemId: 3,
        fileType: 'VIDEO',
      },
    ],
  });

  //attachements for Naleśniki z serem
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'nalesniki-z-serem/nalesniki.jpg',
        menuItemId: 2,
        fileType: 'IMAGE',
      },
      {
        fileName: 'nalesniki-z-serem/patelnia.jpg',
        menuItemId: 2,
        fileType: 'IMAGE',
      },
    ],
  });

  //attachements for Jajecznica z cebulką
  await prisma.attachment.createMany({
    data: [
      {
        fileName: 'jajecznica-z-cebulka/cebulka.jpg',
        menuItemId: 1,
        fileType: 'IMAGE',
      },
      {
        fileName: 'jajecznica-z-cebulka/jajecznica.jpg',
        menuItemId: 1,
        fileType: 'IMAGE',
      },
      {
        fileName: 'jajecznica-z-cebulka/boczek.jpg',
        menuItemId: 1,
        fileType: 'IMAGE',
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
