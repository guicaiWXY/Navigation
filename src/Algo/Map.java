package Algo;

/**
 * Created by 鬼才 on 2015/12/9 0009.
 */
public class Map {
    public static Edge[][] map;
    public static Edge[][] makeMap(){
        //initialize
        map = new Edge[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                map[i][j] = null;
            }
        }
        iniDistance();
        return map;
    }
    private static void iniDistance() {
        map['A'-'A']['B'-'A'] = new Edge('A'-'A', 'B'-'A', 1700);
        map[0][1].setCarOnly();
        map['B'-'A']['A'-'A'] = new Edge('B'-'A', 'A'-'A', 1700);
        map[1][0].setCarOnly();
        map['A'-'A']['D'-'A'] = new Edge('A'-'A', 'D'-'A', 1200);
        map['D'-'A']['A'-'A'] = new Edge('D'-'A', 'A'-'A', 1200);
        map['B'-'A']['C'-'A'] = new Edge('B'-'A', 'C'-'A', 810);
        map['B'-'A']['C'-'A'].setCarOnly();
        map['C'-'A']['B'-'A'] = new Edge('C'-'A', 'B'-'A', 810);
        map['C'-'A']['B'-'A'].setCarOnly();
        map['B'-'A']['G'-'A'] = new Edge('B'-'A', 'G'-'A', 1120);
        map['B'-'A']['G'-'A'].setCanBus(2);
        map['G'-'A']['B'-'A'] = new Edge('G'-'A', 'B'-'A', 1120);
        map['G'-'A']['B'-'A'].setCanBus(2);
        map['C'-'A']['H'-'A'] = new Edge('C'-'A', 'H'-'A', 1030);
        map['H'-'A']['C'-'A'] = new Edge('H'-'A', 'C'-'A', 1030);
        map['D'-'A']['E'-'A'] = new Edge('D'-'A', 'E'-'A', 510);
        map['E'-'A']['D'-'A'] = new Edge('E'-'A', 'D'-'A', 510);
        map['D'-'A']['I'-'A'] = new Edge('D'-'A', 'I'-'A', 1320);
        map['I'-'A']['D'-'A'] = new Edge('I'-'A', 'D'-'A', 1320);
        map['E'-'A']['F'-'A'] = new Edge('E'-'A', 'F'-'A', 680);
        map['F'-'A']['E'-'A'] = new Edge('F'-'A', 'E'-'A', 680);
        map['F'-'A']['G'-'A'] = new Edge('F'-'A', 'G'-'A', 740);
        map['G'-'A']['F'-'A'] = new Edge('G'-'A', 'F'-'A', 740);
        map['F'-'A']['K'-'A'] = new Edge('F'-'A', 'K'-'A', 1140);
        map['F'-'A']['K'-'A'].setCanBus(4);
        map['K'-'A']['F'-'A'] = new Edge('K'-'A', 'F'-'A', 1140);
        map['K'-'A']['F'-'A'].setCanBus(4);
        map['G'-'A']['H'-'A'] = new Edge('G'-'A', 'H'-'A', 970);
        map['H'-'A']['G'-'A'] = new Edge('H'-'A', 'G'-'A', 970);
        map['G'-'A']['L'-'A'] = new Edge('G'-'A', 'L'-'A', 1010);
        map['G'-'A']['L'-'A'].setCanBus(3);
        map['L'-'A']['G'-'A'] = new Edge('L'-'A', 'G'-'A', 1010);
        map['L'-'A']['G'-'A'].setCanBus(3);
        map['H'-'A']['M'-'A'] = new Edge('H'-'A', 'M'-'A', 940);
        map['M'-'A']['H'-'A'] = new Edge('M'-'A', 'H'-'A', 940);
        map['I'-'A']['J'-'A'] = new Edge('I'-'A', 'J'-'A', 950);
        map['J'-'A']['I'-'A'] = new Edge('J'-'A', 'I'-'A', 950);
        map['I'-'A']['N'-'A'] = new Edge('I'-'A', 'N'-'A', 900);
        map['N'-'A']['I'-'A'] = new Edge('N'-'A', 'I'-'A', 900);
        map['J'-'A']['K'-'A'] = new Edge('J'-'A', 'K'-'A', 620);
        map['K'-'A']['J'-'A'] = new Edge('K'-'A', 'J'-'A', 620);
        map['K'-'A']['L'-'A'] = new Edge('K'-'A', 'L'-'A', 660);
        map['L'-'A']['K'-'A'] = new Edge('L'-'A', 'K'-'A', 660);
        map['K'-'A']['P'-'A'] = new Edge('K'-'A', 'P'-'A', 1330);
        map['P'-'A']['K'-'A'] = new Edge('P'-'A', 'K'-'A', 1330);
        map['L'-'A']['M'-'A'] = new Edge('L'-'A', 'M'-'A', 920);
        map['M'-'A']['L'-'A'] = new Edge('M'-'A', 'L'-'A', 920);
        map['L'-'A']['R'-'A'] = new Edge('L'-'A', 'R'-'A', 1100);
        map['L'-'A']['R'-'A'].setCanBus(2);
        map['R'-'A']['L'-'A'] = new Edge('R'-'A', 'L'-'A', 1100);
        map['R'-'A']['L'-'A'].setCanBus(2);
        map['N'-'A']['O'-'A'] = new Edge('N'-'A', 'O'-'A', 830);
        map['O'-'A']['N'-'A'] = new Edge('O'-'A', 'N'-'A', 830);
        map['O'-'A']['P'-'A'] = new Edge('O'-'A', 'P'-'A', 550);
        map['P'-'A']['O'-'A'] = new Edge('P'-'A', 'O'-'A', 550);
        map['O'-'A']['U'-'A'] = new Edge('O'-'A', 'U'-'A', 1300);
        map['U'-'A']['O'-'A'] = new Edge('U'-'A', 'O'-'A', 1300);
        map['P'-'A']['Q'-'A'] = new Edge('P'-'A', 'Q'-'A', 620);
        map['P'-'A']['Q'-'A'].setWalkOnly();
        map['Q'-'A']['P'-'A'] = new Edge('Q'-'A', 'P'-'A', 620);
        map['Q'-'A']['P'-'A'].setWalkOnly();
        map['P'-'A']['T'-'A'] = new Edge('P'-'A', 'T'-'A', 620);
        map['P'-'A']['T'-'A'].setWalkOnly();
        map['T'-'A']['P'-'A'] = new Edge('T'-'A', 'P'-'A', 620);
        map['T'-'A']['P'-'A'].setWalkOnly();
        map['Q'-'A']['R'-'A'] = new Edge('Q'-'A', 'R'-'A', 730);
        map['Q'-'A']['R'-'A'].setWalkOnly();
        map['R'-'A']['Q'-'A'] = new Edge('R'-'A', 'Q'-'A', 730);
        map['R'-'A']['Q'-'A'].setWalkOnly();
        map['Q'-'A']['S'-'A'] = new Edge('Q'-'A', 'S'-'A', 510);
        map['Q'-'A']['S'-'A'].setWalkOnly();
        map['S'-'A']['Q'-'A'] = new Edge('S'-'A', 'Q'-'A', 510);
        map['S'-'A']['Q'-'A'].setWalkOnly();
        map['Q'-'A']['T'-'A'] = new Edge('Q'-'A', 'T'-'A', 930);
        map['Q'-'A']['T'-'A'].setWalkOnly();
        map['T'-'A']['Q'-'A'] = new Edge('T'-'A', 'Q'-'A', 930);
        map['T'-'A']['Q'-'A'].setWalkOnly();
        map['R'-'A']['S'-'A'] = new Edge('R'-'A', 'S'-'A', 870);
        map['S'-'A']['R'-'A'] = new Edge('S'-'A', 'R'-'A', 870);
        map['S'-'A']['Z'-'A'] = new Edge('S'-'A', 'Z'-'A', 380);
        map['Z'-'A']['S'-'A'] = new Edge('Z'-'A', 'S'-'A', 380);
        map['T'-'A']['U'-'A'] = new Edge('T'-'A', 'U'-'A', 1120);
        map['U'-'A']['T'-'A'] = new Edge('U'-'A', 'T'-'A', 1120);
        map['T'-'A']['Z'-'A'] = new Edge('T'-'A', 'Z'-'A', 1890);
        map['Z'-'A']['T'-'A'] = new Edge('Z'-'A', 'T'-'A', 1890);
        map['U'-'A']['V'-'A'] = new Edge('U'-'A', 'V'-'A', 990);    //CAUTION: ONE-WAY
        map['V'-'A']['U'-'A'] = new Edge('V'-'A', 'U'-'A', 990);
        map['V'-'A']['U'-'A'].setWalkOnly();                        //单行道不限制步行
        map['U'-'A']['Z'-'A'] = new Edge('U'-'A', 'Z'-'A', 1700);
        map['Z'-'A']['U'-'A'] = new Edge('Z'-'A', 'U'-'A', 1700);
        map['V'-'A']['X'-'A'] = new Edge('V'-'A', 'X'-'A', 580);
        map['X'-'A']['V'-'A'] = new Edge('X'-'A', 'V'-'A', 580);
        map['V'-'A']['W'-'A'] = new Edge('V'-'A', 'W'-'A', 1100);   //ONE-WAY
        map['W'-'A']['V'-'A'] = new Edge('W'-'A', 'V'-'A', 1100);
        map['W'-'A']['V'-'A'].setWalkOnly();
        map['W'-'A']['X'-'A'] = new Edge('W'-'A', 'X'-'A', 570);    //ONE-WAY
        map['X'-'A']['W'-'A'] = new Edge('X'-'A', 'W'-'A', 570);
        map['X'-'A']['W'-'A'].setWalkOnly();
        map['X'-'A']['U'-'A'] = new Edge('X'-'A', 'U'-'A', 650);    //ONE-WAY
        map['U'-'A']['X'-'A'] = new Edge('U'-'A', 'X'-'A', 650);
        map['U'-'A']['X'-'A'].setWalkOnly();
        map['X'-'A']['Y'-'A'] = new Edge('X'-'A', 'Y'-'A', 1400);
        map['Y'-'A']['X'-'A'] = new Edge('Y'-'A', 'X'-'A', 1400);
        map['Y'-'A']['Z'-'A'] = new Edge('Y'-'A', 'Z'-'A', 970);
        map['Y'-'A']['Z'-'A'].setCanBus(1);
        map['Z'-'A']['Y'-'A'] = new Edge('Z'-'A', 'Y'-'A', 970);
        map['Z'-'A']['Y'-'A'].setCanBus(1);


        //TODO  K T   R Z
        map['K'-'A']['T'-'A'] = new Edge('K'-'A', 'T'-'A', 0);
        map['K'-'A']['T'-'A'].onlyBus(5);
        map['T'-'A']['K'-'A'] = new Edge('T'-'A', 'K'-'A', 0);
        map['K'-'A']['T'-'A'].onlyBus(5);
        map['R'-'A']['Z'-'A'] = new Edge('R'-'A', 'Z'-'A', 0);
        map['R'-'A']['Z'-'A'].onlyBus(2);
        map['Z'-'A']['R'-'A'] = new Edge('Z'-'A', 'R'-'A', 0);
        map['Z'-'A']['R'-'A'].onlyBus(2);

    }
}
