import java.util.ArrayList;

public class RadixSort {
    static int radix = 7;

    /**
     * This variable contains snapshots of the bucketlist that shall be taken at the end of each distribution
     * phase (= each time after all elements to be sorted have been assigned to a bucket).
     * <p>
     * -------------------- List containing the bucketlist snapshots
     * |       ------------ List of buckets
     * |       |         -- Content of a bucket
     * |       |         |
     */
    private static ArrayList<ArrayList<ArrayList<Integer>>> bucketlistSnapshots = new ArrayList<>();

    /**
     * This method returns the bucketlist snapshots.
     *
     * @return bucketlistSnapthos
     */
    public static ArrayList<ArrayList<ArrayList<Integer>>> getBucketlistSnapshots() {
        return bucketlistSnapshots;
    }

    /**
     * Sort a given array using the RadixSort algorithm. After each distribution phase (when all elements have been
     * assigned to buckets) a bucketlist snapshot must be made by calling the provided method addBucketlistSnapshot().
     * <p>
     * As this method is static the bucketlistSnapshots-container must be cleared before each call.
     *
     * @param list contains the elements to be sorted.
     * @return the sorted list
     * @throws IllegalArgumentException if list is null.
     */
    public static ArrayList<Integer> sort(int[] list) throws IllegalArgumentException {
        if (list == null) {
            throw new IllegalArgumentException("List is null!");
        }
        bucketlistSnapshots.clear();

        ArrayList<Integer> sortedList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> buckets = createBuckets();

        int max = getMax(list);
        int digitCount = (int) Math.log10(max) + 1;

        for (int num : list) {
            sortedList.add(num);
        }

        for (int pos = 0; pos < digitCount; pos++) {
            sortByDigit(buckets, sortedList, pos);
            addBucketlistSnapshot(buckets);
            merge(buckets, sortedList);
        }
        return sortedList;
    }

    private static int getMax(int[] elements) {
        int max = Integer.MIN_VALUE;
        for (int elem : elements) {
            if (elem > max) {
                max = elem;
            }
        }
        return max;
    }

    private static void sortByDigit(ArrayList<ArrayList<Integer>> buckets, ArrayList<Integer> sortedList, int pos) {
        for (int elem : sortedList) {
            int digit = getDigit(elem, pos);
            buckets.get(digit).add(elem);
        }
    }

    private static int getDigit(int number, int pos) {
        return (number / (int) Math.pow(10, pos)) % 10;
    }

    private static ArrayList<ArrayList<Integer>> createBuckets() {
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < radix; i++) {
            buckets.add(new ArrayList<>());
        }
        return buckets;
    }

    private static void merge(ArrayList<ArrayList<Integer>> buckets, ArrayList<Integer> sortedList) {
        sortedList.clear();
        for (int i = radix - 1; i >= 0; i--) {
            sortedList.addAll(buckets.get(i));
            buckets.get(i).clear();
        }
    }


    //---------------------------------------------------------------------------------------------------------

    /**
     * This method creates a snapshot (clone) of the bucketlist and adds it to the bucketlistSnapshots.
     * You shall call this method AFTER each distribution phase of the sorting procedure just BEFORE
     * the merge step (collection phase).
     * <p>
     * This method is not part of the algorithm itself, it's just required for detailed unit testing of your
     * implementation.
     *
     * @param bucketlist is your current bucketlist, after assigning all elements to be sorted to the buckets.
     */
    private static void addBucketlistSnapshot(ArrayList<ArrayList<Integer>> bucketlist) {
        // clone list!
        ArrayList<ArrayList<Integer>> lstClone = new ArrayList<>();

        for (int i = 0; i < bucketlist.size(); i++) {
            lstClone.add(new ArrayList<Integer>());
            lstClone.get(i).addAll(bucketlist.get(i));
        }

        // add clone to the bucketlistSnapshots
        bucketlistSnapshots.add(lstClone);
    }
}