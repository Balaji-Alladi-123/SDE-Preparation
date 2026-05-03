package CreationalDesignPattern;

interface Chair {
    void sitOn();
}

interface Sofa {
    void lieOn();
}

class ModernChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("We have to site on the modern chair");
    }
}

class VictorianChair implements Chair {

    @Override
    public void sitOn() {
        System.out.println("We have to site on the victorian chair");
    }
}

class ModernSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("We have to lie on the Modern  sofa");
    }
}

class VictorianSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("We have to lie on the Victorina sofa");
    }
}

interface FurnitureFactory {
    Chair createChair();

    Sofa createSofa();
}

class ModernFurnitureFactory implements FurnitureFactory {
    @Override
   public Chair createChair() {
        return new ModernChair();
    }

    @Override
  public   Sofa createSofa() {
        return new ModernSofa();
    }
}

class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
   public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public  Sofa createSofa() {
        return new VictorianSofa();
    }
}

 class FurnitureShowroom {
      private Chair chair;
      private Sofa sofa;

      public FurnitureShowroom(FurnitureFactory furnitureFactory) {
        this.chair  = furnitureFactory.createChair();
        this.sofa = furnitureFactory.createSofa();
      }

     public void displayFurniture() {
         chair.sitOn();
         sofa.lieOn();
     }
}

class abstractMain {
    public static void main(String[] args) {
        FurnitureFactory factory;

        String style = "victorian"; // change to "modern" to test modern style

        if (style.equalsIgnoreCase("victorian")) {
            factory = new VictorianFurnitureFactory();
        } else {
            factory = new ModernFurnitureFactory();
        }

        FurnitureShowroom showroom = new FurnitureShowroom(factory);
        showroom.displayFurniture();

    }
}
