package mytest;

import org.junit.Test;

/**
 * @author lixinhai
 * @date 2023/3/27
 */
public class Demo {

    public static void main(String[] args){

        // testBubbleSort() 100 times
        for(int i=0; i<100; i++){
            testBubbleSort();
        }
    }

    /**
     * A folder has a name, children, and an optional parent. A child can't be added
     * twice.
     */
    public static class Folder {
        private String name;
        private Folder parent;
        private Folder[] children;
    }


    /**
     * bubble sort desc
     * @param nums
     */
    public static void bubbleSort(int[] nums){

        for(int i=0; i<nums.length-1; i++){
            for(int j=0; j<nums.length-1-i;j++){
                if(nums[j]<nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
    }

    /**
     * function to test bubbleSort
     *
     */
    public static void testBubbleSort() {
        // int array have more than 100 elements
        int[] nums = new int[101];
        for (int i = 0; i < 101; i++) {
            nums[i] = (int) (Math.random() * 100);
        }


        //int[] nums = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0};
        // print nums before sort
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
            // print wrap
            if (i == nums.length - 1) {
                System.out.println();
            }
        }

        bubbleSort(nums);

        // print the result
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }


    }

    /**
     * 生成bubbleSort函数的测试函数
     */


    public double standardDeviation(double[] inputs){
        double sum = 0;
        double avg = 0;
        double standardDeviation = 0;

        for(int i=0; i<inputs.length; i++){
            sum += inputs[i];
        }
        avg = sum/inputs.length;

        for(int i=0; i<inputs.length; i++){
            standardDeviation += Math.pow(inputs[i] - avg, 2);
        }

        standardDeviation = standardDeviation/inputs.length;

        return standardDeviation;
    }

    /**
     * test function of solution49.standardDeviation
     */
    public static void testStandardDeviation(){
        double[] inputs = {1, 2, 3, 4, 5};
        Demo solution49 = new Demo();
        System.out.println(solution49.standardDeviation(inputs));
    }

    /**
     * Function that validates whether a string is a valid email address.
     */
    public static boolean isValidEmail(String email) {
        if(email == null) {
            return false;
        }

        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);

    }


    /**
     * Function that get a http response with RestTemplate
     */


    /**
     * 解析xml文本
     */
    public static void parseXML(String xmlText) {
//        try{
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.parse(new InputSource(new StringReader(xmlText)));
//            Element root = document.getDocumentElement();
//            NodeList nodeList = root.getChildNodes();
//            for(int i=0; i<nodeList.getLength(); i++){
//                Node node = nodeList.item(i);
//                if(node.getNodeType() == Node.ELEMENT_NODE){
//                    Element element = (Element)node;
//                    System.out.println(element.getTagName() + "=" + element.getTextContent());
//                }
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
    }


    /**
     * Function that validates whether a string is a valid mobile phone in china.
     */
    public static boolean isValidMobilePhone(String mobilePhone) {
        if(mobilePhone == null) {
            return false;
        }

        String regex = "^1[3-9]\\d{9}$";
        return mobilePhone.matches(regex);
    }

    @Test
    public void testValidMobilePhone() {

        //String[] testMobilePhones = {"13022218886", "13022218887", "13022218889", "13022218890", "13022218891", "13022218892", "13022218893", "13022218894", "13022218895", "13022218896", "13022218897", "130222188}

        // a string array has more than 100 elements. each length is 11
        String[] mobilePhones = new String[101];
        for (int i = 0; i < 101; i++) {
            mobilePhones[i] = "1" + String.valueOf(Math.random()).substring(2, 12);
        }

        // test mobilePhones
        for (int i = 0; i < 101; i++) {
            System.out.println(mobilePhones[i]);
            System.out.println(isValidMobilePhone(mobilePhones[i]));
        }
    }


    /**
     * create a RocketMq client and send a message to a topic ignore exception
     */
//    public static void createRocketMqClient(String topic, String message) {
//        /**
//         * create a default producer
//         */
//        DefaultMQProducer producer = new DefaultMQProducer("test_producer");
//        producer.setNamesrvAddr("localhost:9876");
//
//        producer.setVipChannelEnabled(false);
//        producer.setRetryTimesWhenSendAsyncFailed(0);
//        producer.setSendMsgTimeout(3000);
//        producer.setRetryTimesWhenSendFailed(0);
//
//        producer.start();
//        Message msg = new Message(topic, message.getBytes());
//        SendResult sendResult = producer.send(msg);
//        System.out.println(sendResult);
//        producer.shutdown();
//    }





}
