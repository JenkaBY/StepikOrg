package org.stepik.java.contest.three;

public class Main26 {
    /*
        Remember all these good old photocopiers? Let's try to implement something similar with time-proven Java arrays
        in dangerous mix with up to date Java Generics.
        Your task is to create Multiplicator that receives Folders with anything that can be copied (i.e. implementing Copy interface)
        any creates array of Folders with copies of the original Folder content. Mix of generics and arrays is not desirable,
        but sometimes we need to work in such a way (e.g. when interacting with legacy code) and it worth to spend
        time to learn how to deal with it.

        Class hierarchy is illustrated by the code snippet:
        interface Copy<T> {
          T copy();
        }
        class Folder<T> {
            private T item;

            public void put(T item) {
              this.item = item;
            }

            public T get() {
                return this.item;
            }
        }

     // Class to work with
        class Multiplicator {
             * Multiply folders and put copies of original folder argument content in each.
             *
             * @param folder folder which content should be multiplicated
             * @param arraySize size of array to return.
             *     Each array element should have Folder with copy of the original content inside
             * @return array of Folder<T>[] objects
            public static <T extends Copy<T>> Folder<T>[] multiply(Folder<T> folder, int arraySize) {
                // Method to implement
            }
        }
        Note the following:
            It's ok to create new Folder instances
            Objects inside newly created Folders should be copies of the original
            Original Folder object should left intact (all array entries are copies of the original object)
    * */
    interface Copy<T> {
        T copy();
    }

    static class Folder<T> {
        private T item;

        public void put(T item) {
            this.item = item;
        }

        public T get() {
            return this.item;
        }
    }

    /**
     * Class to work with
     */
    static class Multiplicator {
        /**
         * Multiply folders and put copies of original folder argument content in each.
         *
         * @param folder    folder which content should be multiplicated
         * @param arraySize size of array to return.
         *                  Each array element should have Folder with copy of the original content inside
         * @return array of Folder<T>[] objects
         */
        public static <T extends Copy<T>> Folder<T>[] multiply(Folder<T> folder, int arraySize) {
            // Method to implement
            Folder<T>[] folders = new Folder[arraySize];
            for (int i = 0; i < arraySize; i++) {
                Folder<T> f = new Folder<>();
                f.put(folder.get().copy());
                folders[i] = f;
            }
            return folders;
        }
    }

    public static void main(String[] args) {
        Folder<StringCopy> folder = new Folder<>();
        folder.put(new StringCopy("Test"));
        Multiplicator.multiply(folder, 5);
    }

    static class StringCopy implements Copy<StringCopy> {
        private String str;

        public StringCopy(String str) {
            this.str = str;
        }

        @Override
        public StringCopy copy() {
            return new StringCopy(new String(str));
        }
    }
}
