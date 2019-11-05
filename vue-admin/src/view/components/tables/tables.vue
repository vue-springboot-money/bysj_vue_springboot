<template>
  <div>
    <Card>
      <tables
        ref="tables"
        editable
        searchable
        search-place="top"
        v-model="tableData"
        :columns="columns"
        @on-delete="handleDelete"
      />
      <Button style="margin: 10px 0;" type="primary" @click="exportExcel">导出为Csv文件</Button>
    </Card>
  </div>
</template>

<script>
import Tables from "_c/tables";
import { getTableData } from "@/api/data";
export default {
  name: "user_management",
  components: {
    Tables
  },
  data() {
    return {
      columns: [
        { title: "Id", key: "id", sortable: true },
        { title: "Username", key: "username", editable: true },
        { title: "NickName", key: "nickname", editable: true },
        { title: "Sex", key: "sex", editable: true },
        { title: "Type", key: "type", editable: true },
        { title: "CreateTime", key: "createtime" },
        {
          title: "Handle",
          key: "handle",
          options: ["delete"],
          button: [
            (h, params, vm) => {
              return h("Poptip", {
                props: {
                  confirm: true,
                  title: "你确定要删除吗?"
                },
                on: {
                  "on-ok": () => {
                    vm.$emit("on-delete", params);
                    vm.$emit(
                      "input",
                      params.tableData.filter(
                        (item, index) => index !== params.row.initRowIndex
                      )
                    );
                  }
                }
              });
            }
          ]
        }
      ],
      tableData: []
    };
  },
  methods: {
    handleDelete(params) {
      console.log(params);
    },
    exportExcel() {
      this.$refs.tables.exportCsv({
        filename: `table-${new Date().valueOf()}.csv`
      });
    }
  },
  mounted() {
    debugger;
    getTableData().then(res => {
      debugger;
      this.tableData = res.data.object;
    });
  }
};
</script>

<style>
</style>
