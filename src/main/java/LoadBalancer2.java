public class LoadBalancer2 {

    public void addResource(String resource) {

        System.out.println("resource " + resource);


    }

    public  String next() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
        sb.append(sb);

        }

        String string = sb.toString();
        return string;
    }



}
