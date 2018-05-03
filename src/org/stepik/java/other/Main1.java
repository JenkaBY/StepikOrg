package org.stepik.java.other;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main1 {
    public static void main(String[] args) {

        /**
         * Accepts a request and returns new request with data wrapped in the tag <transaction>...</transaction>
         */
        final RequestHandler wrapInTransactionTag =
                (Request req) -> new Request(String.format("<transaction>%s</transaction>", req.getData()));

        /**
         * Accepts a request and returns a new request with calculated digest inside the tag <digest>...</digest>
         */
        final RequestHandler createDigest =
                (req) -> {
                    String digest = "";
                    try {
                        final MessageDigest md5 = MessageDigest.getInstance("MD5");
                        final byte[] digestBytes = md5.digest(req.getData().getBytes("UTF-8"));
                        digest = new String(Base64.getEncoder().encode(digestBytes));
                    } catch (Exception ignored) {
                    }
                    return new Request(req.getData() + String.format("<digest>%s</digest>", digest));
                };

        /**
         * Accepts a request and returns a new request with data wrapped in the tag <request>...</request>
         */
        final RequestHandler wrapInRequestTag =
                (req) -> new Request(String.format("<request>%s</request>", req.getData()));

        /**
         * It should represents a chain of responsibility combined from another handlers.
         * The format: commonRequestHandler = handler1.setSuccessor(handler2.setSuccessor(...))
         * The combining method setSuccessor may has another name
         */
        final RequestHandler commonRequestHandler1 = (req) -> new Request(wrapInRequestTag.accept(wrapInTransactionTag).handle(req).getData());// !!! write the combining of existing handlers here
        final RequestHandler commonRequestHandler = (req) -> new Request(wrapInRequestTag.accept(createDigest.accept(wrapInTransactionTag)).handle(req).getData());// !!! write the combining of existing handlers here
//        new Request(createDigest.accept(wrapInRequestTag.accept()));

        final String init = "<type>payment</type><sum>100000</sum><order_id>e94dc619-6172-4ffe-aae8-63112c551570</order><desc>We'd like to buy an elephant</desc>";
        System.out.println(commonRequestHandler.handle(new Request(init)).getData());

        boolean result = !IntStream
                .generate(() -> 100)
                .limit(101)
//                .peek(System.out::println)
                .allMatch(val -> val >= 100);

        System.out.println(result);

        boolean result1 = IntStream
                .iterate(0, n -> n + 1)
                .limit(100)
                .filter(n -> n % 2 != 0)
                .noneMatch(n -> n % 2 == 0);

        System.out.println(factorial(4));

        new StringBuilder("dsd").reverse().toString();
    }

    public static boolean isPrime(final long number) {
        // write your code here
        return LongStream.rangeClosed(2, number)
                .filter((num1) -> LongStream.range(2, number)
                        .allMatch((num2) -> num1 % num2 != 0)).count() != 0;

    }

    public static Stream<String> createBadWordsDetectingStream(String text, List<String> badWords) {
        return Stream.of(text.split(" ")).filter(word -> badWords.contains(word)).distinct().sorted();
        // write your stream here

    }

    public static IntStream createFilteringStream(IntStream evenStream, IntStream oddStream) {
        return IntStream.concat(evenStream, oddStream).sorted().skip(2);// write your stream here
    }

    public static long factorial(long n) {
        return LongStream.rangeClosed(1, n).reduce(1, (acc, number) -> acc * number);// write your code here
    }

    public static long sumOfOddNumbersInRange(long start, long end) {
        return LongStream.rangeClosed(start, end).filter(num -> num % 2 == 0).sum(); // write your code here
    }

    public static long calcNumberOfEmployees(List<Department> departments, long threshold) {
        return departments.stream()
                .filter(dep -> dep.getCode().startsWith("111-"))
                .flatMap(department -> department.getEmployees().stream())
                .filter(emp -> emp.getSalary() >= threshold)
                .count();
    }

    public static class Department {
        private String name;
        private String code;
        private List<Employee> employees;

        public Department(String name, String code, List<Employee> employees) {
            this.name = name;
            this.code = code;
            this.employees = employees;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        public List<Employee> getEmployees() {
            return employees;
        }
    }

    public static class Employee {
        private String name;
        private Long salary;

        public Employee(String name, Long salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public Long getSalary() {
            return salary;
        }
    }

    public static long calcSumOfCanceledTransOnNonEmptyAccounts(List<Account> accounts) {
        // write your code here
        return accounts.stream()
                .filter(account -> account.getBalance() > 0)
                .flatMapToLong(account -> account.getTransactions().stream()
                        .filter(transaction -> transaction.getState() == State.CANCELED)
                        .mapToLong(Transaction::getSum))
                .sum();
    }

    public static class Transaction {
        private String uuid;
        private State state;
        private Long sum;

        private Date created;

        public Transaction(String uuid, State state, Long sum, Date created) {
            this.uuid = uuid;
            this.state = state;
            this.sum = sum;
            this.created = created;
        }

        public String getUuid() {
            return uuid;
        }

        public State getState() {
            return state;
        }

        public Long getSum() {
            return sum;
        }

        public Date getCreated() {
            return created;
        }

    }

    public static class Account {
        private String number;
        private Long balance;
        private List<Transaction> transactions;

        public Account(String number, Long balance, List<Transaction> transactions) {
            this.number = number;
            this.balance = balance;
            this.transactions = transactions;
        }

        public String getNumber() {
            return number;
        }

        public Long getBalance() {
            return balance;
        }

        public List<Transaction> getTransactions() {
            return transactions;
        }
    }

    enum State {CANCELED, FINISHED, PROCESSING;}
//    Transaction: uuid: String,  state: State (CANCELED, FINISHED, PROCESSING), sum: Long, created: Date
//    Account: number: String, balance: Long, transactions: List<Transaction>}
}
