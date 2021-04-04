class Problem {
    public static void main(String[] args) {

        boolean isMode = false;

        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("mode")) {
                System.out.println(args[i + 1]);
                isMode = true;
                break;
            }
        }

        if (!isMode) {
            System.out.println("default");
        }

    }
}