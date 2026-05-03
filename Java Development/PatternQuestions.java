public class PatternQuestions {
    public static void main(String args[]) {
        pattern32();
    }

    public static void pattern1() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col <= row; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void pattern2() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void pattern3() {
        for (int row = 0; row < 5; row++) {
            for (int col = 5; col > row; col--) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void pattern4() {
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= row; col++) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    /*      *
            **
            ***
            ****
            *****
            ****
            ***
            **
            *
            pattern5()
     */
    public static void pattern5() {
        int n = 4;
        for (int row = 1; row < (2 * n); row++) {
            int totalColsInRow = row > n ? 2 * n - row : row;
            for (int col = 1; col <= totalColsInRow; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void pattern6() {
        for (int row = 1; row <= 5; row++) {
            for (int k = 5; k > row; k--) {
                System.out.print(" ");
            }
            for (int col = 1; col <= row; col++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void pattern7() {
        for (int row = 1; row <= 5; row++) {
            for (int col = 5; col >= row; col--) {
                System.out.print("*");
            }
            System.out.println();
            for (int k = 1; k <= row; k++) {
                System.out.print(" ");
            }
        }
    }

    public static void pattern8() {
        for (int row = 1; row <= 9; row++) {
            for (int k = 9; k >= row; k--) {
                System.out.print(" ");
            }
            if (row % 2 != 0) {
                for (int col = 1; col <= row; col++) {
                    System.out.print("* ");
                }
            } else {
                System.out.println();
            }
        }
    }

    public static void pattern9() {
        for (int row = 1; row <= 9; row++) {
            if (row % 2 != 0) {
                for (int col = 9; col >= row; col--) {
                    System.out.print("* ");
                }
            } else {
                System.out.println();
            }
            for (int k = 1; k <= row; k++) {
                System.out.print(" ");
            }
        }
    }

    /* * * * * *
       * * * *
        * * *
         * *
          *
          *
          * *
         * * *
        * * * *
       * * * * *
     output for  Pattern12()
     */

    public static void pattern12() {
        int n = 10, i = 2, spaces = 0;
        for (int row = 1; row <= (2 * n); row++) {
            int colToRow = row > n ? (row - n) : n - row + 1;
            for (int col = 1; col <= colToRow; col++) {
                System.out.print("* ");
            }
            System.out.println();
            spaces = (row >= n) ? (row - i + 1) : row;
            if (row >= n) i += 2;
            for (int k = 1; k <= spaces; k++) {
                System.out.print(" ");
            }
        }
    }

    public static void pattern21() {
        int r = 1;
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= row; col++) {
                System.out.print(r + " ");
                r++;
            }
            System.out.println();
        }
    }

    public static void pattern22() {
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= row; col++) {
                if (row % 2 == 0) {
                    if (col % 2 != 0) {
                        System.out.print("0 ");
                    } else {
                        System.out.print("1 ");
                    }
                } else {
                    if (col % 2 != 0) {
                        System.out.print("1 ");
                    } else {
                        System.out.print("0 ");
                    }
                }
            }
            System.out.println();
        }
    }

    /*   *        *
     **      **
     ***    ***
     ****  ****
     **********
     ****  ****
     ***    ***
     **      **
     *        *
     * pattern29()
     */
    public static void pattern29() {
        int n = 5, col = 0;
        for (int row = 1; row < (2 * n); row++) {
            int totalColsInRow = row > n ? 2 * n - row : row;
            for (col = 1; col <= totalColsInRow; col++) {
                System.out.print("*");
            }
            int spaces = row > n ? (row - col) + 1 : ((2 * n) - (row + col));
            for (int k = 1; k <= spaces; k++) {
                System.out.print(" ");
            }
            for (int col2 = 1; col2 <= totalColsInRow; col2++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void pattern28() {
        int n = 5;
        for (int row = 1; row < (2 * n); row++) {
            int colToRow = row > n ? row - (row - n) * 2 : row;
            int spaces = row > n ? (row) : (2 * n) - row;
            for (int k = 1; k <= spaces; k++) {
                System.out.print(" ");
            }
            for (int col = 1; col <= colToRow; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

   /*
    E
    D E
    C D E
    B C D E
    A B C D E
    by pattern32()
*/
    public static void pattern32() {
        int ch=69,i=1;
        for(int row =69;row>=65;row--){
            for(int col=row;col>=row;col++) {
                if((col-i)== row) {
                break;
                }
                else {
                    System.out.print(Character.toChars(col));
                }
            }
            i++;
            System.out.println();
        }
    }
}
