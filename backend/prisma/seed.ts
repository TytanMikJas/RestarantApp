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
          'puszysta jajecznica z chrupiącym boczkiem, podawana z świeżym pieczywem i cebulką.',
        alergens: ['jajka', 'mleko', 'gluten'],
        price: 15.99,
        images: ['jajecznica.jpg'],
        video: 'jajecznica.mp4',
        category: Category.BREAKFAST,
      },
      {
        name: 'Naleśniki z serem',
        description:
          'delikatne naleśniki nadziewane słodkim serem, podane z sosem waniliowym i owocami.',
        alergens: ['gluten', 'mleko', 'jajka'],
        price: 18.99,
        images: ['nalesniki_z_serem.jpg'],
        video: 'nalesniki_z_serem.mp4',
        category: Category.BREAKFAST,
      },
      {
        name: 'Kanapka ze smalcem',
        description:
          'chrupiąca kanapka z domowym smalcem i ogórkami kiszonymi, posypana szczypiorkiem.',
        alergens: ['gluten'],
        price: 12.99,
        images: ['kanapka_ze_smalcem.jpg'],
        video: 'kanapka_ze_smalcem.mp4',
        category: Category.BREAKFAST,
      },
      {
        name: 'Żurek na Zakwasie',
        description:
          'tradycyjna polska zupa żurek z białą kiełbasą, jajkiem i świeżym majerankiem, podawana w chlebie.',
        alergens: ['gluten', 'jajka', 'seler'],
        price: 24.99,
        images: ['zurek_na_zakwasie.jpg'],
        video: 'zurek_na_zakwasie.mp4',
        category: Category.LUNCH,
      },
      {
        name: 'Kapuśniak z Kiełbasą',
        description:
          'gęsta zupa z kwaszonej kapusty z dodatkiem kiełbasy i ziemniaków, doprawiona majerankiem.',
        alergens: ['seler'],
        price: 19.99,
        images: ['kapusniak_z_kielbasa.jpg'],
        video: 'kapusniak_z_kielbasa.mp4',
        category: Category.LUNCH,
      },
      {
        name: 'Barszczyk Czerwony z Uszkami',
        description:
          'klasyczny barszcz czerwony podawany z uszkami nadziewanymi grzybami.',
        alergens: ['gluten', 'seler'],
        price: 21.99,
        images: ['barszczyk_czerwony_z_uszkami.jpg'],
        video: 'barszczyk_czerwony_z_uszkami.mp4',
        category: Category.LUNCH,
      },
      {
        name: 'Kotlet Schabowy',
        description:
          'panierowany kotlet schabowy podawany z ziemiaczkami i surówką z białej kapusty.',
        alergens: ['gluten', 'jajka', 'mleko'],
        price: 29.99,
        images: ['kotlet_schabowy_z_kopytkami.jpg'],
        video: 'kotlet_schabowy_z_kopytkami.mp4',
        category: Category.LUNCH,
      },
      {
        name: 'Łazanki z Kapustą i Grzybami',
        description:
          'tradycyjne łazanki z kiszona kapustą, grzybami leśnymi i kawałkami mięsa, doprawione majerankiem.',
        alergens: ['gluten', 'seler'],
        price: 23.99,
        images: ['lazanki_z_kapusta_i_grzybami.jpg'],
        video: 'lazanki_z_kapusta_i_grzybami.mp4',
        category: Category.LUNCH,
      },
      {
        name: 'Sernik na Zimno',
        description:
          'kremowy sernik na zimno, z delikatną warstwą galaretki na wierzchu, na kruchym spodzie z ciasteczek.',
        alergens: ['gluten', 'mleko', 'jajka'],
        price: 14.99,
        images: ['sernik_na_zimno.jpg'],
        video: 'sernik_na_zimno.mp4',
        category: Category.DESSERT,
      },
      {
        name: 'Szarlotka Jabłkowa',
        description:
          'lekki, owocowy deser z warstwami świeżych jabłek i delikatnej pianki, posypany cynamonem.',
        alergens: ['gluten', 'jajka', 'mleko'],
        price: 12.99,
        images: ['szarlota_jablkowa.jpg'],
        video: 'szarlota_jablkowa.mp4',
        category: Category.DESSERT,
      },
      {
        name: 'Pączki z Różą',
        description:
          'tradycyjne polskie pączki, nadziewane słodkim dżemem różanym, obsypane cukrem pudrem.',
        alergens: ['gluten', 'jajka', 'mleko'],
        price: 9.99,
        images: ['paczki_z_roza.jpg'],
        video: 'paczki_z_roza.mp4',
        category: Category.DESSERT,
      },
      {
        name: 'Kompot z Suszu',
        description:
          'tradycyjny, domowy napój przygotowany z suszonych owoców, idealny do obiadu.',
        alergens: [],
        price: 4.99,
        images: ['kompot_z_suszu.jpg'],
        video: 'kompot_z_suszu.mp4',
        category: Category.DRINKS,
      },
      {
        name: 'Miód Pitny',
        description:
          'słodki, rozgrzewający napój alkoholowy na bazie miodu, serwowany na ciepło.',
        alergens: [],
        price: 8.99,
        images: ['miód_pitny.jpg'],
        video: 'miód_pitny.mp4',
        category: Category.DRINKS,
      },
      {
        name: 'Herbata z Przypinką',
        description:
          'mocna, czarna herbata z dodatkiem polskiego spirytusu, podawana z plasterkiem cytryny.',
        alergens: [],
        price: 7.99,
        images: ['herbata_z_przypinka.jpg'],
        video: 'herbata_z_przypinka.mp4',
        category: Category.DRINKS,
      },
      {
        name: 'Piwo Tyskie czteropak',
        description:
          'jedno z najpopularniejszych polskich piw, charakteryzujące się złocistym kolorem i wyraźną goryczką.',
        alergens: ['gluten'],
        price: 6.99,
        images: ['piwo_tyskie.jpg'],
        video: 'piwo_tyskie.mp4',
        category: Category.DRINKS,
      },
      {
        name: 'Chleb na Zakwasie',
        description:
          'domowy chleb na zakwasie, o chrupiącej skórce i miękkim, aromatycznym miąższu.',
        alergens: ['gluten'],
        price: 5.99,
        images: ['chleb_na_zakwasie.jpg'],
        video: 'chleb_na_zakwasie.mp4',
        category: Category.ADDITIONALS,
      },
      {
        name: 'Ogórek Kiszony',
        description:
          'chrupiące, domowe ogórki kiszone, doskonałe jako dodatek do każdego posiłku.',
        alergens: [],
        price: 3.99,
        images: ['ogorek_kiszony.jpg'],
        video: 'ogorek_kiszony.mp4',
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
